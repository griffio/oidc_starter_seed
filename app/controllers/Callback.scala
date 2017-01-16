package controllers

import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, AnyContent, Controller}
import configuration.AppConfiguration
import play.api.cache.CacheApi

class Callback @Inject() (config: AppConfiguration, cache: CacheApi, ws: WSClient) extends Controller {
  
  def callback(codeOpt: Option[String] = None): Action[AnyContent] = Action.async {
    (for {
      code <- codeOpt
    } yield {
      getToken(code).flatMap { case (idToken, accessToken) =>
       getUser(accessToken).map { user =>
          cache.set(idToken+ "profile", user)
          Redirect(routes.User.index())
            .withSession(
              "idToken" -> idToken,
              "accessToken" -> accessToken
            )  
      }
        
      }.recover {
        case ex: IllegalStateException => Unauthorized(ex.getMessage)
      }  
    }).getOrElse(Future.successful(BadRequest("No parameters supplied")))
  }

  def getToken(code: String): Future[(String, String)] = {
    val tokenResponse = ws.url(String.format("https://%s/token", config.domain())).
      withHeaders(HeaderNames.ACCEPT -> MimeTypes.JSON).
      post(
        Json.obj(
          "client_id" -> config.clientId(),
          "client_secret" -> config.clientSecret(),
          "redirect_uri" -> config.callbackURL,
          "code" -> code,
          "grant_type"-> "authorization_code"
        )
      )

    tokenResponse.flatMap { response =>
      (for {
        idToken <- (response.json \ "id_token").asOpt[String]
        accessToken <- (response.json \ "access_token").asOpt[String]
      } yield {
        Future.successful((idToken, accessToken)) 
      }).getOrElse(Future.failed[(String, String)](new IllegalStateException("Tokens not sent")))
    }
  }
  
  def getUser(accessToken: String): Future[JsValue] = {
    val userResponse = ws.url(String.format("https://%s/userinfo", config.domain()))
      .withQueryString("access_token" -> accessToken)
      .get()

    userResponse.flatMap(response => Future.successful(response.json))
  }
}