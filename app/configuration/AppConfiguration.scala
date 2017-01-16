package configuration

import javax.inject.{Inject, Singleton}

import com.google.inject.Provider
import play.Application

@Singleton
class AppConfiguration @Inject()(application: Provider[Application]) {
  implicit lazy val current: Application = application.get()

  def clientSecret(): String = current.configuration.getString("oidc.clientSecret")

  def clientId(): String = current.configuration.getString("oidc.clientId")

  def callbackURL(): String = current.configuration.getString("oidc.callbackURL")

  def domain(): String = current.configuration.getString("oidc.domain")
}
