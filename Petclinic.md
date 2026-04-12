# Petclinic

Analyse the Spring Petclinic Microservice example.

[Github](https://github.com/spring-petclinic/spring-petclinic-microservices)

## Questions

### 1. How are the services configured?

Spring Cloud Config handles the service configuration.

### 2. Where do the configurations come from?

Each microservice includes the `spring-cloud-starter-config` dependency which allows them to fetch settings from a centralized Config Server.

### 3. Is Spring actuator used? Where is this visible?

Spring Actuator is used multiple times across the microservices, this is visible by looking at the `pom.xml` files of the services and searching for a `spring-boot-starter-actuator` dependency.

### 4. What are the responsibilities of the API Gateway?

- Routing: routes requests to the microservices + `StripPrefix`
- Aggregation: composites data from multiple services and exposes it to the frontend
- Resilience: it implements `Circuit Breakers` and `Retries` to handle service failure more gracefully
- Static Content: serves the Angular JS frontend application

### 5. Which services are user-facing vs internal?

Only the gateway service is user facing, the rest is only reachable via the gateway (at least from a user perspective).

### 6. How does the gateway know via which IP/port the services can be reached?

Service discovery using `Netflix Eureka`, allowing the gateway service to reference the microservices via their application names, and the discovery service looks up the IP address and load-balances between multiple instances.

### 7. How does service discovery affect routing and resilience?

Dynamic Routing eliminates the need for hardcoded IP addresses. Services can be started on any available port and the gateway will automatically find them.

If a service instance fails, the Discovery Server (Eureka) eventually removes it from the registry. The gateway's load balancer then stops sending traffic to that failed instance, improving system availability.

### 8. How do the services communicate with each other? What are the advantages/disadvantages of this?

Synchronous REST communication over HTTP is used for internal communication.

Pro: \

Simple to implement, easy to test, and uses standard HTTP protocols that are widely understood.

Con: \
Services are tightly coupled via their APIs. If a downstream service is slow or fails, it can cause a "bottleneck" or cascading failure in the caller (though this is partially mitigated here by circuit breakers).

### 9. What alternatives are there, what are the advantages/disadvantages?

- Asynchronous Messaging (e.g., Kafka, RabbitMQ): Services communicate by publishing/subscribing to events. \
    Pros:
    Better decoupling, higher scalability, and improved fault tolerance. \
    Cons:
    Increased complexity and eventual consistency.

- gRPC: A high-performance RPC framework using Protocol Buffers. \
    Pros:
    Faster than REST, strongly typed. \
    Cons:
    Less human-readable, requires specific client/server generation.

### 10. Do users know which type of communication is used? Why/why not?

No, the users only communicate with the gateway service, any and all internal communication is completely abstracted from the user.

### 11. Why does the chat not work?

Missing API keys to OpenAI or Azure's OpenAI

### 12. How would you add a completely new microservice (e.g., a billing-service) to this system? What are all the places you would need to make changes?

To add a billing-service, you would need to:

1. Create Module: Create a new Maven module under the parent directory.

2. Parent POM: Add <module>spring-petclinic-billing-service</module> to the modules section of the root pom.xml.

3. Dependencies: Include spring-cloud-starter-config and spring-cloud-starter-netflix-eureka-client in the new service's pom.xml.

4. Configuration: Create an application.yml for the new service in the Config Server's Git repository.

5. Gateway Route: Add a new route in spring-petclinic-api-gateway/src/main/resources/application.yml (e.g., mapping /api/billing/** to lb://billing-service).

6. Docker (Optional): If using Docker, add the new service to the docker-compose.yml.

### 13. Is there any form of authentication or authorization in the system? If not, what are the security risks of running it as-is? What approach would you choose to implement authentication/authorization?

No, based on the provided files, there is no evidence of authentication or authorization.

Risks: \
As-is, any user can access, modify, or delete sensitive data like owner addresses, pet medical history (visits), and vet details. There is no protection against unauthorized API calls.

Proposal: \
Implement Spring Security with OAuth2/OpenID Connect (OIDC). Specifically, I would configure the API Gateway as an OAuth2 Resource Server or use an Identity Provider (like Keycloak or Auth0) to issue JWT (JSON Web Tokens). The gateway would validate the token, and the services would authorize requests based on the user's roles extracted from the JWT.