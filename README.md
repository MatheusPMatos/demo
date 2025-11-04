# Projeto Java Spring Boot - Engenharia de Software

Este é um projeto de exemplo desenvolvido na disciplina de Engenharia de Software, utilizando Java com Spring Boot e banco de dados MySQL.

## Pré-requisitos

- Java 17+
- Maven 3.6+
- Docker (opcionalmente, para rodar o banco de dados MySQL em container)

## Tecnologias utilizadas

- Spring Boot (Web, Data JPA)
- MySQL
- Lombok

## Instalação do Banco de Dados MySQL

Você pode rodar o MySQL localmente ou usando Docker Compose. Para facilitar, este projeto inclui um arquivo `docker/docker-compose.yaml`:

```bash
cd docker
docker compose up -d
```

As variáveis `MYSQL_DATABASE` e `MYSQL_ROOT_PASSWORD` podem ser configuradas em um arquivo `.env` no diretório `docker`.

## Configuração da Aplicação

O arquivo `src/main/resources/application.properties` já está configurado para um banco de dados MySQL local. Certifique-se que os dados (usuário, senha, banco) estão corretos conforme sua configuração:

```
spring.datasource.url=jdbc:mysql://localhost:3306/aula?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
```

## Execução da Aplicação

1. **Baixe as dependências e compile:**

   ```bash
   ./mvnw clean install
   ```

2. **Suba o banco de dados (caso use Docker):**

   ```bash
   cd docker
   docker compose up -d
   ```

3. **Rode a aplicação:**

   ```bash
   ./mvnw spring-boot:run
   ```

A aplicação estará disponível em:  
http://localhost:8080

## Endpoints disponíveis

### Passageiros (Passengers)

- `GET /passengers` - Lista todos os passageiros
- `GET /passengers/{id}` - Busca um passageiro por ID
- `POST /passengers` - Cria um novo passageiro
- `PUT /passengers/{id}` - Atualização completa de um passageiro
- `PATCH /passengers/{id}` - Atualização parcial (nome) de um passageiro
- `DELETE /passengers/{id}` - Remove um passageiro

### Motoristas (Drivers)

- `GET /drivers` - Lista todos os motoristas
- `GET /drivers/{id}` - Busca um motorista por ID
- `POST /drivers` - Cria um novo motorista
- `PUT /drivers/{id}` - Atualização completa de um motorista
- `PATCH /drivers/{id}` - Atualização parcial (nome, data de nascimento) de um motorista
- `DELETE /drivers/{id}` - Remove um motorista

## Estrutura do Projeto

```
src/
  main/
    java/br/edu/ifpr/demo/
      api/
        DriverController.java
        PassengerController.java
      domain/
        Driver.java
        Passenger.java
    resources/
      application.properties
docker/
  docker-compose.yaml
```

## Observações

- O projeto utiliza o Lombok para reduzir código boilerplate nas entidades. Instale o plugin do Lombok na sua IDE se necessário.
- Os dados do banco de dados são atualizados automaticamente com Hibernate (DDL auto-update).

## Autor

Projeto de exemplo para a disciplina de Engenharia de Software - IFPR.

