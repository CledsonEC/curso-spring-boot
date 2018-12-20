# curso-spring-boot
Projeto utilizado no curso spring-boot do canal dev-dojo

# Urls Úteis
```sh
http://<baseurl>/v2/api-docs
http://<baseurl>/swagger-ui.htm
```

# Exemplos Postman

### Login
```sh
POST /login 
Host: localhost:8081
Content-Type: application/json
{"username":"cledson", "password":"devdojo"}
```

### Recuperar Todos os estudantes
```sh
GET /v1/protected/students/ 
Host: localhost:8081
```
### Recuperar estudante por Id
```sh
GET /v1/protected/students/1 
Host: localhost:8081
```

### Recuperar estudante por Nome
```sh
GET /v1/protected/students/findByName/cleds
Host: localhost:8081
```

### Recuperar estudante com paginação
```sh
GET /v1/protected/students?page=1&size=5
Host: localhost:8081
```

### Criar um estudante
```sh
POST /v1/admin/students
Host: localhost:8081
Content-Type: application/json
{
	"name":"teste 2",
	"email":"cd@t.com"
}
```

### Alterar um estudante
```sh
PUT /v1/admin/students
Host: localhost:8081
Content-Type: application/json
   {
        "id": 2,
        "name": "Blah2"
    }
```

### Deletar um estudante
```sh
DELETE /v1/admin/students/7
Host: localhost:8081
Content-Type: application/json
```


