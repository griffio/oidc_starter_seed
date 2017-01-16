# Play 2.5 Scala Regular WebApp Seed
This is the seed project you need to use if you're going to create regular Webapp with Scala and Play2. If you want to create a Play Scala API

Removed deprecated features warning of PlayFramework

#Running the example
In order to run the example you need to have `play` and `activator` installed.

You also need to set the ClientSecret, ClientId, CallbackURL and Domain in the `application.conf`. Just look for the placeholders and fill them with the right information


```` OIDC properties
# ~~~~~~~~~~~~~~~~~

oidc.clientSecret="YOUR_CLIENT_SECRET"
oidc.clientId="YOUR_CLIENT_ID"
oidc.domain="YOUR_DOMAIN"
oidc.callbackURL="http://localhost:9000/callback"
````

Finally, run `activator run` to start the app and try calling [http://localhost:9000/](http://localhost:9000/)
