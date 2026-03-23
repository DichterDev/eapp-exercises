# Setup

Run the following to build the Jar files.

```sh
cd 02-ecommerce-microservices
chmod u+x gradlew
gradlew build -x test
```

Docker Depoloyment:

```sh
docker compose up -d
```

This starts:
- UserDB
- ProductDB
- OrderDB
- Gateway
- User Service
- Order Service
- Product Service

> [!WARNING]
> You need to build the project first before depolying the containers, the containers need the jar files.

# Information

The API Gateway Swagger ui also publishes the API docs of the services but uses the internal Docker Container names in the request which breaks the requests.
I dont want to put in the work to fix this problem, but still.
