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
