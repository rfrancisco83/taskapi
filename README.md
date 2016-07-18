# taskapi
Spring based REST API in Java 8

Run with: mvn package && java -jar target/taskapi-0.0.1-SNAPSHOT.jar

Sample URLs:

GET
http://localhost:8080/user/1
http://localhost:8080/user/2
http://localhost:8080/user/3
http://localhost:8080/user/4
http://localhost:8080/user/1/taskList

http://localhost:8080/user/1/taskList

POST
http://localhost:8080/user/1/create/taskList
http://localhost:8080/user/1/taskList/1/create/task/test+description/false

http://localhost:8080/user/1/taskList/2/create/task/test+description/false

http://localhost:8080/user/1/taskList/2/create/task/test+description/false

http://localhost:8080/user/1/taskList/2/update/task/test+description/false


