# Getting Started

### What you need
- gradle (it is tested with version 7.3.2). You can download gradle from [Gradle](https://gradle.org/)
- Java 17 (it is tested with openjdk version "17" 2021-09-14). You can download the Open JDK from [Open JDK](https://openjdk.org/)

### How to build the project
Go to the root folder and just type **gradle build**. 
For the Jacoco reports you can run **gradle build jacocoTestReport** (in build/reports/jacoco)
When you build the project the unit tests will run automatically.

### How to run the project
Go to the root folder and type **java -jar build/libs/hoover-0.0.1-SNAPSHOT.jar**

### How to test the project
You need to
- issue a POST request to the following URL: http://localhost:8080/hoover/cleaning
- set the content type to "application/json"
- Send the following JSON request 
`{
    "roomSize" : [5, 5],
    "coords" : [1, 2],
    "patches" : [
        [1, 0],
        [2, 2],
        [2, 3]
    ],
    "instructions" : "NNESEESWNWW"
}`
- You should get another JSON response
`{
    "coords" : [1, 3],
    "patches" : 1
}`

### Documentation
- You can look at the [Swagger] (http://localhost:8080/swagger-ui.html) documentation (when the web application is running).
- You can run the **gradle javadoc** command in order to generate javadocs (in build/docs/javadoc folder). The javadocs are created with the assumption that they will be used internally (i.e. by the developers themselves). If they need to be distributed to external partners then the **gradle.build** file should be modified and the *-package* option should be removed.
