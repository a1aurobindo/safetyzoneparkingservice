# Read Me

### Reference Documentation

Platform Reference

* Java version: 11
* SpringBoot version: 2.7.10
* Maven Version: 3.6.3

### Run Locally

#### The .env file contains information regarding cognito client, the slot size, rate limiter configurations, server port, I will remove the secrets from this once testing is done

The following guides illustrate how to run the service locally

* Change values in the .env file and put inside the project root folder
* Run [mvn clean install] inside the repository folder
* Run [mvn spring-boot:run] in the same target folder

## Authentication & Authorization

* Open http://localhost:8080/token in a browser
* A page with aws cognito login page will come up
* put username and password and sign in
* It will get back list of tokens
* Copy the access_token value

## Resource Endpoints

* **/park** [**POST**] takes body of type json in { carRegNum: string} format
* **/unpark** [**PUT**] takes body type json in { carRegNum: string} format
* **/slot** [**GET**] takes request parameters slotId (Integer) and history (Boolean , ooptional)

#### Open postman, create a request
### Park a car
* Add url http://localhost:8080/park
* Set Method type to **POST**
* Add in the body {"carRegNum": "CA 12 2345"}
* Add an **Authorization** header with value in **Bearer <access_token>** format
* Send

### Unpark a car
* Add url http://localhost:8080/unpark
* Set Method type to **PUT**
* Add in the body {"carRegNum": "CA 12 2345"}
* Add an **Authorization** header with value in **Bearer <access_token>** format
* Send

### Get Parking slot information
* Add url http://localhost:8080/slot?slotId=1&history=true
* Set Method type to **GET**
* Add an **Authorization** header with value in **Bearer <access_token>** format
* Send

# Note
* If the token expires over time, again open the link http://localhost:8080/token in a browser to get a new token
* Alternatively postman can also run the request to get the auth tokens



