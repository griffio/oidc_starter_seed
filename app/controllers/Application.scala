package controllers

import javax.inject.Inject

import configuration.AppConfiguration
import play.api.mvc.Action
import play.api.mvc.Controller

class Application @Inject() (config: AppConfiguration) extends Controller {
  def index = Action {
    Ok(views.html.index(config))
  }
}