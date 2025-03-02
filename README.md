<h1 align="center" id="title">Springboot Gateway</h1>

<p id="description">This project was developed using the knowledge gained from my study of how to implement a gateway using spring boot. The main goal was to implement a simple gateway capable of redirecting routes to the product-service microservice.</p>

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Service Discovery Integration(Eureka)
*   API Gateway for Microservices

<h2>🛠️ Installation Steps:</h2>

<p>1. Clone the repository</p>

<p>2. Build the modules</p>

```
mvn clean install -DskipTests
```

<p>3. Create each module's docker image</p>

```
docker build .
```

<p>4. Start the docker-compose</p>

```
docker compose up
```

  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Java 17
*   Spring Boot Framework
*   Docker
*   Maven
