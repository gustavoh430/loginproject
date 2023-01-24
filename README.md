# Registration and Login System with JWT

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gustavoh430/loginproject/blob/main/LICENCE) 

![Header](./github-header-image.png)

I have been learning Java and some technologies lately. 

Nowadays, I work with SRE and Observability, but I'm trying to expand my view in the developing field. So as a first project, I will be creating a Registration and Login system using Spring as framework, Maven as repository manager, MySQL as database and, finally, JwT as authorization token.


## What's in the box?

This project has been built up based on Logical layers of a Web Service as follows:

![Header](https://github.com/gustavoh430/loginproject/blob/main/ServiceLayer.drawio.png)

This is a registration and login system using JWT to authenticate. It has got one service layer that includes all operations available, that are: Sign in, Sign up, User Information and User Role Setting up resource.


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


## JWT
Jason Web Token, also known as JWT, is a token digitally assigned to assure secure information transmission.
It is usually used during authentication. Once the user has already proven he can be trusted, a key will be generated and it can be used to access different resources that only will be available through the JWT token.

![image](https://user-images.githubusercontent.com/41215245/214250247-19335bda-0345-4e98-8de7-db7bf9652292.png)
(Image Source: https://metamug.com/article/security/jwt-java-tutorial-create-verify.html)
