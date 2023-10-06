<h4> What Is an ID Token? </h4>

An ID token is encoded as a JSON Web Token (JWT), a standard format that allows your application to easily inspect its content, and make sure it comes from the expected issuer and that no one else changed it.


<h4> What Is an Access Token? </h4>

In the OAuth 2 context, the access token allows a client application to access a specific resource to perform specific actions on behalf of the user. 


<h4> How to get the tokens </h4>

1. open Insomnia
2. go to authorization tab, and change to "OAuth 2"
3. fill in information
   1. GRANT TYPE:  Authorization Code
   2. AUTHORIZATION URL: http://localhost:9000/oauth2/authorize
   3. ACCESS TOKEN URL: http://localhost:9000/oauth2/token
   4. CLIENT ID: client
   5. CLIENT SECRET: secret
   6. USE PKCE: check
   7. CODE CHALLENGE METHOD: SHA-256
   8. REDIRECT URL: http://127.0.0.1:8080/login/oauth2/code/myoauth2
   9. under advanced options:
      1. SCOPE: openid

4. fetch the tokens


<h4> Where to inspect ID Token or ACCESS Token </h4>

1. https://jwt.io/
2. past in the id token
