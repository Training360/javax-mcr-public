# Employees

http://localhost:8081/auth/realms/EmployeesRealm/.well-known/openid-configuration
http://localhost:8081/auth/realms/EmployeesRealm/protocol/openid-connect/certs
http://localhost:8081/auth/realms/EmployeesRealm/protocol/openid-connect/token

```properties
keycloak.auth-server-url=http://localhost:8081/auth
keycloak.realm=EmployeesRealm
keycloak.resource=employees-app
keycloak.bearer-only=true
keycloak.public-client=true

keycloak.security-constraints[0].auth-roles[0]=employees_app_user
keycloak.security-constraints[0].security-collections[0].patterns[0]=/*

keycloak.principal-attribute=preferred_username
```