[![Build Status](https://travis-ci.org/modestukasai/pre-configured-project.svg?branch=master)](https://travis-ci.org/modestukasai/pre-configured-project)

# Pre configured project for quick start

## Used tools
* Spring Boot
* Spring Cloud
* Gradle
  * FindBugs (plugin)
  * CheckStyle (plugin)
  * CodeNarc (plugin)
  * PMD (plugin)

## Project capabilities
* Configuration microservice uses properties from `https://github.com/modestukasai/pre-configured-project-config`
* Gateway is Zuul edge service, connects all client side microservices
* Service discovery service uses Eeureka
* Authorization microservice uses OAuth2
* Generate jar version from git commit hash
* Automatially upload artifacts to nexus

## Project launch

Launch in this order (bootRun task):

1. `configuration`
2. `service-discovery`
3. `authorization` 
4. `project-a:api`
5. `gateway`


// CONTINUE 
https://github.com/tinmegali/Using-Spring-Oauth2-to-secure-REST

