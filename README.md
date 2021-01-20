## Sobre a API

Este projeto é uma API desenvolvida como parte do teste para Fullstack da Alelo Frota. É uma API para gerenciamento de veículos. Foi desenvolvida utilizando Java, Spring Boot e Spring Framework. Apesar de simples, este projeto utilizou diversas características para a construção de APIs REST (quase RESTFul). A URL da api é `/api-alelo`.

## Funcionalidades

Os endpoints providos por esta API são:

* Criar um novo veículo: `POST /api-alelo/vehicle`
* Atualizar um veículo na API: `PUT /api-alelo/vehicle/:id`
* Deletar um veículo na API: `DELETE /api-alelo/vehicle/:id`
* Listar Veículos (com filtro de Placa): `GET /api-alelo/vehicle?filter=AAA1234`
* Listar Veículos (com filtro de Status): `GET /api-alelo/vehicle?filter=true`
* Listar Veículos (com paginação): `GET /api-alelo/vehicle?limit=10&page=2`
* Busca um veículo específico pelo Id: `GET /api-alelo/vehicle/:id`

### Detalhes dos Endpoints

`POST /api-alelo/vehicle`

Este endpoint é utilizado para criar um novo veículo.

**Payload body:**

```json
{
  "color": "Blue",
  "manufacturer": "Volkswagen",
  "model": "Jetta",
  "plate": "JAH-4120",
  "status": true
}
```


`PUT /api-alelo/vehicle/:id`

Este endpoint é utilizado para atualizar o veículo.

**Payload body:**

```json
{
  "id": 1,
  "color": "Black",
  "manufacturer": "Volkswagen",
  "model": "Jetta",
  "plate": "JAH-4120",
  "status": true
}
```

Note que todos os atributos do objeto são enviados de volta. Mas a placa (plate) e o id não alterados e portanto não podem ir com NOVOS valores. O retorno dos endpoints acima está definido abaixo:

```json
{
  "data": {
    "id": 1,
    "plate": "JAH-4120",
    "model": "Jetta",
    "manufacturer": "Volkswagen",
    "color": "Blue",
    "status": true,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/api-alelo/vehicle/1"
      }
    ]
  }
}
```

`GET /api-alelo/vehicle?limit=10&page=2`

Este endpoint retorna uma lista de veículos. Aceita algums parâmetros:

`filter` - pode ser true/false ou uma placa

`limit` - total de registros por página

`page` - página desejada no resultado


`GET /api-alelo/vehicle/:id`

Este endpoint retorna um veículo especificado a partir do id informado.

**Onde:**

`id` - id do veículo desejado.

`DELETE /api-alelo/vehicle/:id`

Este endpoint deleta um veículo especificado a partir do id informado.

**Onde:**

`id` - id do veículo desejado.


### Tecnologias Utilizadass

Este projeto foi desenvolvida com as ferramentas:

* **Java 15 (Java Development Kit - JDK: 15.0.1)**
* **Spring Boot 2.4.2**
* **Spring Admin Client 2.3.1**
* **Maven**
* **JUnit 5**
* **Swagger 3.0.0**
* **Model Mapper 2.3.9**

### Compilação e Empacotamento

Esta APi também foi desenvolvida para executar como um `jar`. Para gerar este `jar`, deve-se executar:

```bash
mvn package
```

Isso executará o clean, compile e generate do maven e salvará um arquivo `jar` no diretório target.


### Testes

* Para execução de testes unitários:

```bash
mvn test
```

* Para executar todos os testes, incluindo Testes de Integração:

```bash
mvn integration-test
```

### Execução

Para executar a API, simplesmente execute o jar:

```bash
java -jar frota-1.0.0-SNAPSHOT.jar
```
    
or

```bash
mvn spring-boot:run
```

Por padrão, a API estará disponível em [http://localhost:8080/api-alelo](http://localhost:8080/api-alelo)

### Documentação e Testes funcionais

* Swagger (somente ambiente de desenvolvimento): [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
