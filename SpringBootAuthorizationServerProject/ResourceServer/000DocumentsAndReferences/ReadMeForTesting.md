<h4> steps to test resource server </h4>

1. open Insomnia, which can be downloaded from https://docs.insomnia.rest/insomnia/authentication
2. get the access token in Insomnia: create a GET request and set the authentication to OAuth2:
   1. GRANT TYPE: Authorization Code
   2. AUTHORIZATION URL: http://localhost:9000/oauth2/authorize
   3. ACCESS TOKEN URL: http://localhost:9000/oauth2/token
   4. CLIENT ID: client
   5. CLIENT SECRET: secret
   6. REDIRECT URL: 
   7. click on "Fetch Tokens" (you will be prompt for user credentials)
3. access page with access token
   1. create a GET request
   2. set the authentication to Bearer
   3. set the fields:
      1. TOKEN: 123423!@#$$5......
      2. PREFIX: bearer
