package configuration

import javax.inject.{Inject, Singleton}

@Singleton
class AppConfiguration @Inject()(configuration: play.Configuration) {

  def clientSecret(): String = configuration.getString("oidc.clientSecret")

  def clientId(): String = configuration.getString("oidc.clientId")

  def callbackURL(): String = configuration.getString("oidc.callbackURL")

  def domain(): String = configuration.getString("oidc.domain")
}
