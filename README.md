# Loja de Suplementos

## Docker
Para simplificar a execução do projeto, disponibilizamos uma versão containerizada da aplicação.
Para executar basta rodar o comando, garantindo que o Docker esteja previamente instalado:
```bash
docker compose up -d
```

Esse comando irá executar um container com o banco MongoDB e subir a aplicação java.
Após isso, poderá ser acessado via navegador na url `localhost:8080`

## Build e Execução Manual

### Requisitos
- Java 17 ou superior
- Docker

Para subir o container com o banco MongoDB, execute o comando abaixo:
```bash
docker compose up -d mongodb
```

Em seguida é possível compilar e executar a aplicação java através do comando:
```bash
    ./mvnw spring-boot:run
```

Acesse a aplicação através `http://localhost:8080` no seu navegador.
