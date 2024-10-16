# Sistema de Caixa Bancário - Projeto Fullstack

Este é um projeto fullstack de um **sistema de caixa bancário**, desenvolvido para demonstrar a manipulação de contas bancárias e transações. O projeto é composto por uma API backend desenvolvida em **Java** (com **Spring Boot**) e um frontend web desenvolvido com **React** e **TypeScript**.

## Estrutura do Projeto

O projeto contém dois diretórios principais:

- **AlvesBank**: Contém a API desenvolvida com Spring Boot.
- **front-challenge**: Contém a interface web desenvolvida com React [front-end](https://github.com/Gideonalves05/front-desafio).

No diretório raiz, também há um arquivo `docker-compose.yml`, que é utilizado para inicializar o banco de dados e a API dentro de containers Docker.

## Requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas e configuradas:

- [Docker](https://www.docker.com/get-started) (com Docker Compose habilitado)
- [Node.js](https://nodejs.org/) (com `npm` disponível no PATH)
- [jdk21] (com as variaveis de ambientes configuradas)

Além disso, é necessário garantir que o Docker esteja em execução na sua máquina.

## Executando o Projeto

### Execução Padrão
Siga os passos abaixo para executar o projeto localmente:

1. Clone este repositório ou faça o download do diretório principal.
2. No diretório raiz do projeto, abra o terminal (ou PowerShell no Windows).
3. Execute o comando abaixo para iniciar o banco de dados dentro de containers Docker:
4. Execute a aplicação Spring 
    ```bash
    docker-compose up -d
    ```
    **Obs:** para cirar os containers do docker é preciso que as portas configuradas no **Docker-compose** estejam disponíveis 
    - Banco: 5432
   

    Caso necessario, alterar portas do banco postgres no arquivo **Docker-compose**.
    
5. Navegue até o diretório do frontend:
    ```bash
    cd front-challenge
    ```
6. Instale as dependências do frontend:
    ```bash
    npm install
    ```
7. Inicie o servidor de desenvolvimento do frontend:
    ```bash
    npm run dev
    ```

Após a execução desses comandos, será exibido no terminal o endereço (URL) onde a aplicação está hospedada, geralmente algo como `http://localhost:5173`.

## Banco de Dados

O banco de dados utilizado é o **PostgreSQL**, executado em um container Docker para simplificar o processo de instalação e evitar problemas de compatibilidade. 

## Backend - API

O backend é uma API desenvolvida em **Java** com o framework **Spring Boot**, utilizando **Maven** como gerenciador de dependências. A API fornece endpoints para realizar operações em contas bancárias e transações financeiras.

## Frontend - Interface Web

O frontend é uma aplicação simples desenvolvida com **React** e **TypeScript**, utilizando **Vite** como ferramenta de build. A interface foi criada para facilitar o teste do sistema, permitindo a interação com as funcionalidades principais de maneira visual.

## Funcionalidades do Sistema

O sistema de caixa bancário permite realizar as seguintes operações:

- **Listar contas**: Visualizar todas as contas bancárias cadastradas.
- **Criar conta**: Adicionar uma nova conta bancária.
- **Depósito**: Realizar um depósito em uma conta.
- **Saque**: Realizar um saque de uma conta.
- **Transferência**: Transferir dinheiro entre contas bancárias.

## Considerações Finais

Este projeto tem como objetivo principal demonstrar a integração de um sistema backend e frontend para a manipulação de contas bancárias e transações. O uso do Docker simplifica a configuração do ambiente e o React com TypeScript oferece uma interface amigável para interação com o sistema.
