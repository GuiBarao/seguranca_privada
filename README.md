<h1>API para sistema de gestão de segurança privada</h1> 


![Java](https://img.shields.io/badge/Java-17.0.16-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.3-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-3.9.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-9.22.0-red?style=for-the-badge&logo=flyway&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0.32-blue?style=for-the-badge&logo=mysql&logoColor=white)


## Como executar localmente 

:heavy_check_mark: Dependências base

  - Instale o JAVA 17.
  - Instale o MySQL.
  - Instale o Maven (gerenciador de dependências).

:heavy_check_mark: Configuração de ambiente

  - Crie um banco de dados para o sistema.
  - Em "src/main/resources" renomeie o arquivo "application.properties.exemplo" para "application.properties".
  - Em "application.properties" preencha as informações:
    - HOST DO BANCO
    - PORTA DO BANCO
    - NOME DO BANCO
    - NOME DE USUÁRIO DO BANCO
    - SENHA DO BANCO  
  - Na raiz do projeto rode o comando "mvn install" para instalar as dependências do sistema.

:heavy_check_mark: Rodar servidor localmente

  - Na raiz do projeto execute o comando "mvn spring-boot:run".
  - Ao rodar o servidor o spring boot roda as migrações do flyway e cria a estrutura do banco informado em application.properties.
  - Spring Boot roda por padrão na porta 8080.
