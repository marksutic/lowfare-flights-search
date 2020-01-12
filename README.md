## lowfare-flights-search

This is a maven multi module project consisting of:

* **[lowfare-flights-search-service](./lowfare-flights-search-service)** - Spring Boot based backend
* **[lowfare-flights-search-client](./lowfare-flights-search-client)** - Angular 8 client application

### Prerequisites

* [Java 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)

### Major dependencies:
* Maven wrapper: 3.6.3
* Spring Boot: 2.2.2
* Angular: 8.2.14
 
### Dev setup
Clone project :
```
git clone git@github.com:marksutic/lowfare-flights-search.git
```
Run initial mvn package on parent level to install local node and npm through maven-frontend-plugin:
```
mvnw.cmd clean package
```
Run spring boot service:
```
cd lowfare-flights-search-service
..\mvnw.cmd spring-boot:run
```
Run ng-cli([note](#notes)):
```
cd.. && cd lowfare-flights-search-client
ng.cmd serve --open
```
Open your browser on ```http://localhost:4200/```  and start coding :)



### Building artifact
After initial mvn clean at parent level, run mvn package:
```
cd lowfare-flights-search-service
..\mvnw.cmd clean package
```
This will copy angular client built in dist folder to be included in classes/static location inside jar or war, depending on your setup.


Run the application:
```
java -jar target\lowfare-flights-search-service-0.0.1.jar
```
Open your browser on ```http://localhost:8088/``` to see the application.

Application in action:
![Alt text](./in_action.jpg?raw=true "Optional Title")

###### Notes
* [npm.cmd](./lowfare-flights-search-client/npm.cmd) and [ng.cmd](./lowfare-flights-search-client/ng.cmd) are  wrapper scripts to run local node and angular cli with correct paths 
