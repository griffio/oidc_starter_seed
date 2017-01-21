# Play 2.5 Scala Regular WebApp Seed
This is the seed project you need to use if you're going to create regular Webapp with Scala and Play2. If you want to create a Play Scala API

Removed deprecated features warning of PlayFramework

#Running the example

```
sbt run
```

You also need to set the ClientSecret, ClientId, CallbackURL and Domain in the `application.conf`. Just look for the placeholders and fill them with the specific information.


```
oidc.clientSecret="YOUR_CLIENT_SECRET"
oidc.clientId="YOUR_CLIENT_ID"
oidc.domain="YOUR_DOMAIN"
oidc.callbackURL="http://localhost:9000/callback"
```

Call App to compile [http://localhost:9000/](http://localhost:9000/)
