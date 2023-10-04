<h4>Get Authorization Code</h4>
1. under local development, find main endpoints from http://localhost:9000/.well-known/oauth-authorization-server
2. website to get the authorization code (use query mode to send request)
   1. https://oauthdebugger.com/
   2. https://oidcdebugger.com/
   
   use http://localhost:9000/oauth2/authorize  (get) to get the authorization code



<h4> Get access token</h4>

   1. open postman
   2. use http://localhost:9000/oauth2/token (post) to get the access token
   3. for Authorization, use Basic Auth, and client_id, client_secret
   4. for Params, specify:
      1. redirect_uri:  value
      2. grant_type:  authorization_code
      3. code: {{the authorization code received from step2}}
      4. state: if applicable

<h4> Response to receive the access token: </h4>

<code>
   {
   "access_token": "eyJraWQiOiJkODMwN2QxZS1mMGU1LTQ2NmItYmRhMC1iYjc3NDM0MGE5ZjciLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiY2xpZW50IiwibmJmIjoxNjk2NDM1NDM3LCJzY29wZSI6WyJyZWFkIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTY5NjQzNTczNywiaWF0IjoxNjk2NDM1NDM3fQ.IK1XA3LPHQ2uCFlo0i8C2_mS_IfYcmq-B6et6nc44PTIwfhVCErMLNjxlu2AbJovj9SHDuCN9GBKNjBOsj-b3DZLWKKVtrEKA40tw-O_A7UlaqAaDfE8gEwLMhXjp301vlZ5xK7U0ovLV_Ff1m8G8AW6xTAYdV3Ypljj1wZ7iILKMrWi4T6d8GGCJLD2oC3alQpu5SP_N4kU-YR_2gq9TtM54VbF1vkcmz8j7FHtDa0yhRpiibCi-k0A47S_73xqkXUYQITx9ll00sxiBEMD6nngnZZkI_ASZWqzGV6Q8hy7ETWC7e09qjjl2PCSSyA7jKpJqytLATcsqZJuB499Uw",
   "scope": "read",
   "token_type": "Bearer",
   "expires_in": 299
   }
</code>

<h4>Other endpoints:</h4>
1. find introspect endpoint from: http://localhost:9000/oauth2/introspect, using basic auth and client_id, client_secret
2. find revocation endpoint from: http://localhost:9000/oauth2/revoke, using basic auth and client_id, client_secret
