# Chat App

A Spring Boot prototype with a message REST API, a configured STOMP/SockJS endpoint, and PostgreSQL persistence. Use synthetic messages in local development. The HTTP endpoints do not enforce user authentication.

## Build and test

Use Java 17 or later. Maven Wrapper retrieves the declared Maven version.

```bash
sh ./mvnw test
```

The integration test uses embedded H2. To run the app, create a local PostgreSQL database and export its connection settings.

```bash
export CHAT_DB_URL=jdbc:postgresql://localhost:5432/chatappdb
export CHAT_DB_USERNAME=chatapp
read -rsp 'Local database password: ' CHAT_DB_PASSWORD
export CHAT_DB_PASSWORD
sh ./mvnw spring-boot:run
```

The server listens on `127.0.0.1:8081`. `GET /api/messages` lists stored messages. `POST /api/messages` accepts an object with `sender` and `content`. `CHAT_ALLOWED_ORIGIN` configures the origin permitted for the SockJS endpoint. The optional JWT utility reads a Base64-encoded signing key of at least 32 bytes from `CHAT_JWT_SECRET`.
