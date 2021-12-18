## Summary

TUI DX Backend technical Test v2

The base project uses lombok, so you have to install it. You can use the following
guide https://www.baeldung.com/lombok-ide

User credentials for JWT authentication: {"username": "admin", "password": "12345678"}

## Parameter to config

There are some parameter if you want to config.

```
order:
    cook-time-in-seconds: 10
    wait-to-time-in-seconds: 30
```

## Swagger

```
http://localhost:8080/tui-api/swagger-ui.html
```

## Urls

### Managment

```
http://localhost:8081/tui-api/management
```

## Sonarqube

```
mvn clean package sonar:sonar
```

## Docker compose

```
docker-compose build 
docker-compose up -d
```

## Client

There are 3 clients created in H2.

## Login to use Search orders

```
curl -X POST "http://localhost:8080/tui-api/1.0/auth/login" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"12345678\"}"
```

## Create order

```
 curl -X POST "http://localhost:8080/tui-api/1.0/orders" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"deliveryAddress\":{\"street\":\"Mateu Monserrat\",\"postcode\":\"07013\",\"city\":\"Mallorca\",\"country\":\"Spain\"},\"pilotes\":\"FIVE\",\"clientId\":1}"

```

## Search order

### Search by client data (firstName and lastName)

You need to login before and change the token

```
curl -X POST "http://localhost:8080/tui-api/1.0/orders/search" -H  "accept: application/json" -H  "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzOTU4Mzk5NywiZXhwIjoxNjM5NTkxMTk3fQ.zgsq8Y0WbLg4NDBaT7pH_iUF0uDNZl64u9gAMVd6LeKMGYgz0qol_Tjd5tS9AO4SykDMOKANgujX0uIU_LGfSw" -H  "Content-Type: application/json" -d "{\"client\":{\"firstName\":\"Test\",\"lastName\":\"Lastname\"}}"
```

### Search by order id

You need to login before and change the token

```
curl -X POST "http://localhost:8080/tui-api/1.0/orders/search" -H  "accept: application/json" -H  "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzOTU4Mzk5NywiZXhwIjoxNjM5NTkxMTk3fQ.zgsq8Y0WbLg4NDBaT7pH_iUF0uDNZl64u9gAMVd6LeKMGYgz0qol_Tjd5tS9AO4SykDMOKANgujX0uIU_LGfSw" -H  "Content-Type: application/json" -d "{\"orderId\":1}"
```