# microservices_sb

## Eureka Server Link: http://localhost:8761
## API Gateway Link: http://localhost:8080

## H2 web Console Link: http://localhost:8082

## Todo Service Link: http://localhost:8083/api/todo/

# Make sure you are inside the specific folder containing this microservice's pom.xml
mvn clean install -DskipTests

## Authentication (obtain token)
Obtain a token (replace username/password):
Credentials:

user1@example.com / password1

user2@example.com / password2

curl -s -X POST http://localhost:8080/v1/auth/token \
  -H 'Content-Type: application/json' \
  -d '{"username":"user1@example.com","password":"password1"}'

# Extract token (requires jq)
TOKEN=$(curl -s -X POST http://localhost:8080/v1/auth/token -H 'Content-Type: application/json' -d '{"username":"user1@example.com","password":"password1"}' | jq -r .token)

Use the token to call Todo service (examples):

# Create a todo (POST /api/todo)
curl -X POST http://localhost:8083/api/todo \
  -H "Authorization: Bearer $TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{"name":"Test Todo","description":"This is a test todo","priority":1,"status":"Pending"}'

# Get a todo by id (GET /api/todo?id=1)
curl -X GET 'http://localhost:8083/api/todo?id=1' \
  -H "Authorization: Bearer $TOKEN"

Notes:
- Token endpoint: POST /v1/auth/token on the API Gateway (http://localhost:8080).
- Todo service endpoints are under http://localhost:8083/api/todo. The GET uses a query param id; POST accepts JSON with fields name, description, priority, status.

