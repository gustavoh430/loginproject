# Registration and Login System with JWT

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gustavoh430/loginproject/blob/main/LICENCE) 


## What's in the box?

This project has been built up based on Logical layers of a Web Service as follows:

![Header](https://github.com/gustavoh430/loginproject/blob/main/ServiceLayer.drawio.png)

This is a registration and login system using JWT to authenticate. It has got one Controller layer that includes all operations available, that are: Sign in, Sign up, User Information retrieving and User Role Setting up.
There will be a service layer which will orchestrate all service callings between the controllers and the database repository. Of course, I could just call the repository directly through the Controller, but I thought it would be most suitable, for this case, to create an intermediate class. This class is responsible for containing all businesses rules.


## Sign Up (HTTP POST)


### Endpoint
```text
/login/signup
```

### Payload
```json
{
    "name": "Gustavo",
    "age": "34",
    "email": "gustavoh430@gmail.com",
    "password": "teste123"

}
```
### Response
```json

"User registered successfully"
```

## Sign In

### Endpoint
```text
/login/signin
```

### Payload
```json
{
    "email": "gustavoh430@gmail.com",
    "password": "teste123"
}
```

### Response
```json
{
    "accessToken": "<ACCESS_TOKEN>",
    "tokenType": "Bearer "
}
```

## User Information Retrieving (HTTP GET)

You must be logged in with a valid JwT token*

### Endpoint
```text
/login/{email}
```

### Response
```json
{
    "id": 1,
    "name": "Gustavo",
    "age": "34",
    "email": "gustavoh430@gmail.com",
    "password": "$2a$10$G8u.55if26heBdmte13fw.anY0PjoP//H/m1SPx.aOl1/gXA2PwR.",
    "passwordConfirm": null,
    "roles": [
        {
            "id": 2,
            "name": "USER",
            "authority": "USER"
        }
    ],
    "enabled": true,
    "username": "Gustavo",
    "authorities": [
        {
            "id": 2,
            "name": "USER",
            "authority": "USER"
        }
    ],
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true
}
```


## User Role Setting up (HTTP POST)

In this case, you do not need to be logged in with a valid JwT token, but this is customizable (Of course, that in a real project this functionality would only be accessible to the highest authority).

### Endpoint
```text
/login/authorities
```

### Payload
```json
{
    "email": "gustavoh430@gmail.com",
    "role": "ADMIN"
}
```

### Response
```text

"Authorithy has been changed successfully"

```




## JWT
Jason Web Token, also known as JWT, is a token digitally assigned to assure secure information transmission.
It is usually used during authentication. Once the user has already proven he can be trusted, a key will be generated and it can be used to access different resources that only will be available through the JWT token.

![image](https://user-images.githubusercontent.com/41215245/214250247-19335bda-0345-4e98-8de7-db7bf9652292.png)
(Image Source: https://metamug.com/article/security/jwt-java-tutorial-create-verify.html)
