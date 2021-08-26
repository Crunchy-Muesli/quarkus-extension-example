Minimal working example of quarkus security extension with cache problem (created for an open issue)

Consisting of 2 folders:  

- quarkus-security-extension: the security extension that intercepts http calls directed to the protected service
- quarkus-protected-service: basic rest service


**How to build**

Enter the **quarkus-security-extension** folder and build with  
`mvn clean install`

Enter the **quarkus-protected-service** and run with  
`mvn compile quarkus:dev`

Call the protected rest service:  
`curl --location --request GET 'localhost:8080/customer'`

This should trigger the cache exception.



