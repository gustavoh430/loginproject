# Registration and Login System with JWT

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gustavoh430/loginproject/blob/main/LICENCE) 


## What's in the box?

This project has been built up based on Logical layers of a Web Service as follows:

![Header](https://github.com/gustavoh430/loginproject/blob/main/ServiceLayer.drawio.png)

This is a registration and login system using JWT to authenticate. It has got one Controller layer that includes all operations available, that are: Sign in, Sign up, User Information and User Role Setting up resource.
There will be a service layer which will orchestrate all service callings between the controllers and the database repository. Of course, I could just call the repository directly through the Controller, but I thought it would be most suitable, for this case, to create an intermediate class. This class is responsible for containing all businesses rules.


### Sign Up Endpoint
```text
/login/signup
```

### Sign Up Payload
```json
{
    "name": "Gustavo",
    "age": "34",
    "email": "gustavoh430@gmail.com",
    "password": "teste123"

}
```
### Sign Up Response
```json

"User registered successfully"
```


### Sign In Endpoint
```text
/login/signin
```

### Sign In Payload
```json
{
    "email": "gustavoh430@gmail.com",
    "password": "teste123"
}
```

### Sign In Response
```json
{
    "accessToken": "<ACCESS_TOKEN>",
    "tokenType": "Bearer "
}
```

## JWT
Jason Web Token, also known as JWT, is a token digitally assigned to assure secure information transmission.
It is usually used during authentication. Once the user has already proven he can be trusted, a key will be generated and it can be used to access different resources that only will be available through the JWT token.

![image](https://user-images.githubusercontent.com/41215245/214250247-19335bda-0345-4e98-8de7-db7bf9652292.png)
(Image Source: https://metamug.com/article/security/jwt-java-tutorial-create-verify.html)
