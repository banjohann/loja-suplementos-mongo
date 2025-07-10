# Loja de Suplementos

### 1. Scripts de Criação do banco:
Para subir o container com o banco MongoDB, execute o comando abaixo:
```bash
docker compose up -d mongodb
```
**O Framework Spring consegue criar as collections do banco sem necessitar de ajuda externa, basta executar o servidor.**

Caso queira criar manualmente as collections, o passo-a-passo é:

#### 1. Se conectar ao mongoshell do docker:
```bash
docker exec -it mongodb_container mongosh --authenticationDatabase admin -u mongodb-admin -p umasenhanadasegura
```

#### 2. Criar as seguintes collections:
```js
// Usar o schema do projeto:
use loja-suplementos

// Para a coleção 'nutritional_tables'
db.createCollection("nutritional_tables");

// Para a coleção 'products'
db.createCollection("products");

// Para a coleção 'brands'
db.createCollection("brands");

// Para a coleção 'sales'
db.createCollection("sales");

// Para a coleção 'customers'
db.createCollection("customers");
```
Esses script criará todas as collections ("tabelas") no mongoDB

## 2. Build e Execução 
### 2.1 Docker
Para simplificar a execução do projeto, disponibilizamos uma versão containerizada da aplicação.
Para executar basta rodar o comando, garantindo que o Docker esteja previamente instalado:
```bash
docker compose up -d
```

Esse comando irá executar um container com o banco MongoDB e subir a aplicação em outro container.
Após isso, poderá ser acessado via navegador na url `http://localhost:8080`

### 2.2 Manual

Caso queira executar o servidor manualmente, os requisitos são:
- Java 17 ou superior
- Docker

1. Execute o container docker com o banco MongoDB
```bash
docker compose up -d mongodb
```

Em seguida é possível compilar e executar a aplicação java através do comando:
```bash
    ./mvnw spring-boot:run
```

Acesse a aplicação através `http://localhost:8080` no seu navegador.
