# Play 2.5 Scala Regular WebApp Seed

OpenID Connect authentication form to call /authorise with a code grant flow and callback 

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
