# Registration and Login System with JWT

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gustavoh430/loginproject/blob/main/LICENCE) 


## What's in the box?

This project has been built up based on Logical layers of a Web Service as follows:

![Header](https://github.com/gustavoh430/loginproject/blob/main/ServiceLayer.drawio.png)

This is a registration and login system using JWT to authenticate. It has got one Controller layer that includes all operations available, that are: Sign in, Sign up, User Information retrieving and User Role Setting up.
There will be a service layer which will orchestrate all service callings between the controllers and the database repository. Of course, I could just call the repository directly through the Controller, but I thought it would be the most suitable, for this case, to create an intermediate class. This class is responsible for containing all business rules.


# User Resource (Controllers)

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

## Sign In (HTTP POST)

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



# User Service Layer


As mentioned before, this class is responsible for holding all business rules related to the project. This layer must also treat the exceptions that can ocurr during service callings.


```java
@Service
public class UsersService {

	@Autowired
	UsersRepository userRepository;
	@Autowired
	RolesRepository rolesRepository;

	public Users findByEmail(String email) {
		Optional<Users> obj = Optional.of(userRepository.findByEmail(email));
		return obj.orElseThrow(() -> new ResourceNotFoundException(email));
	}

	public Users findByPassword(String password) {
		Optional<Users> obj = Optional.of(userRepository.findByPassword(password));
		return obj.orElseThrow(() -> new ResourceNotFoundException(password));
	}

	public boolean nonNullFields(String users) {
		Boolean obj = users.isBlank();
		return obj;

	}

	public Boolean existsByEmail(String username) {
		return userRepository.existsByEmail(username);

	}

	public Users save(Users user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role roles = rolesRepository.findByName("USER").get();
		user.setRoles(Collections.singletonList(roles));
		return userRepository.save(user);

	}

	public void SetAuthoritiesService(SetAuthoritiesDTO authorities) {

		List<Role> listRoles = new ArrayList<>();

		try {
			Role roles = rolesRepository.findByName(authorities.getRole()).get();
			Users user = Optional.of(userRepository.findByEmail(authorities.getEmail())).get();

			listRoles.add(roles);
			user.setRoles(listRoles);
			userRepository.save(user);

		} catch (Exception e) {

			throw new ResourceNotFoundException("Email or Role does not exist");

		}

	}

	public Role saveRole(Role roles) {
		return rolesRepository.save(roles);

	}



}

```

# Security Layer

It has been used the SpringSecurity framework to make the access secure. Below, there is an schema of how this framework works.

First of all, the income HTTP requests will be filtered. There are a bunch of filters we can apply. Clearly, it will depend on which kind of operation we want to do.
But as example of this, we can decide which endpoints will be accessible to each user role. We can also decide if people must be logged in to have certain access.

After hiting all of the filters applied, the Authentication Manager will be called. Its responsibility is to go through all of the providers trying to authenticate the users. Hence, the providers are supposed to fetch users by communicating with User Details Service and return success state if the user exist with given credential. These users will be fetched from the MySQL database, in this case.

![Header](https://github.com/gustavoh430/loginproject/blob/main/Spring%20Web%20Securiy.png)




## JWT
Jason Web Token, also known as JWT, is a token digitally assigned to assure secure information transmission.
It is usually used during authentication. Once the user has already proven he can be trusted, a key will be generated and it can be used to access different resources that only will be available through the JWT token.

The general schema of how it works is showed below:

![image](https://user-images.githubusercontent.com/41215245/214250247-19335bda-0345-4e98-8de7-db7bf9652292.png)
(Image Source: https://metamug.com/article/security/jwt-java-tutorial-create-verify.html)


However, to understand what has been done in this project completely, we must get our head around how it is related to the Spring Security and where it belongs in the schema showed before.


## SpringSecurity + JWT
