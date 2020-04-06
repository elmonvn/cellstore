# Cellstoreapi

Este projeto consiste em uma REST API para cadastro de celulares de acordo com característica comuns às lojas _online_ (modelo, marca, preço etc.).

O objetivo principal é de ser consumida por uma camada de _front-end_ (aqui, a aplicação **_Cellstoreweb_**).

## Componentes e pré-requisitos

Esta API utiliza a seguinte lista de componentes:

* [Spring Data Rest](https://spring.io/projects/spring-data-rest)
* [Spring Data Mongo](https://spring.io/projects/spring-data-mongodb)
* [MongoDB](https://www.mongodb.com/)
* [Java 11](https://openjdk.java.net/projects/jdk/11/)
* [Apache Tomcat](https://tomcat.apache.org/) (opcional)
* [Intellij IDEA Ultimate](https://www.jetbrains.com/idea/) (ambiente de desenvolvimento) 
* [Swagger](https://swagger.io/) (ambiente de desenvolvimento)
* [Docker](https://www.docker.com/)

## _Deploy_ do _Cellstoreapi_

### Ambiente de desenvolvimento

Recomenda-se a utilização de _application servers_ e SGBDs embutidos no IDE selecionado para codificação. 

Outra alternativa viável é a utilização do banco de dados em contêiner Docker 

É **obrigatório** que o MongoDB esteja em execução e acessível em _localhost_ e em sua porta padrão (27017) antes de ser iniciado a API.

Logo após, é necessário se deslocar ao diretório onde está a aplicação (inicialmente <code>./cellstoreapi</code>) e executar o utilitário _Gradle_ da seguinte forma:

    ./gradlew bootRun

A API será servida pelo endereço <code>http://localhost:8080/api/phones</code>. Mais detalhes sobre os _endpoints_ definidos, consultar o _Swagger_ no ambiente de desenvolvimento.

**Obs**: também é possível conteinerização da API em _Docker_.

### Ambiente de produção

É possível executar a aplicação em modo _standalone_ ou em servidor de aplicação (J2EE) de preferência, por meio do _deploy_ do arquivo `WAR`.

No primeiro caso, o ideal é executar o arquivo JAR gerado no build (`cellstoreapi-0.0.1-SNAPSHOT.jar`), da seguinte forma:

    java -jar cellstoreapi-0.0.1-SNAPSHOT.jar

Para o _deploy_ da API em servidor de aplicação J2EE, é necessário o arquivo `WAR` gerado pelo _build_ e o servidor escolhido. Em particular, este projeto foi customizado para _deploy_ no [Apache Tomcat](https://tomcat.apache.org/).

Em ambos os casos (produção em `JAR` ou `WAR`), a API será servida pelo endereço <code>http://localhost:8080/api/phones</code>. Mais detalhes sobre os _endpoints_ definidos, consultar o _Swagger_ no ambiente de desenvolvimento.

### Contêineres

É possível disponibilizar ambos os ambientes como contêineres **_Docker_**, como também nas arquiteturas _standalone_ e servidor de aplicação J2EE.

Para fins de consistência e manutenção da estrutura (API, banco de dados etc.), o mais indicado é o emprego da ferramenta [docker-compose](https://docs.docker.com/compose/).

## Documentação da API (_Swagger_)

A documentação da API e seus _endpoints_ pode ser visualizada, em ambiente de desenvolvimento, pelo recurso _Swagger_.

Para tanto, é necessário iniciar a aplicação com

    ./gradlew bootRun
    
e acessar o endereço <code>[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)</code>.

## _Build_

Conforme adiantado acima, a aplicação pode sofrer _deploy_ em modo _standalone_ (`JAR`) ou em servidor de aplicação J2EE (`WAR`).

Normalmente, ambos os tipos de arquivos podem ser construídos em IDEs da plataforma Java/Spring, como no Intellij IDEA.

Se a preferência for o terminal, as seguintes linhas de comando devem ser executadas:

* `JAR` : `./gradlew bootJar` 

* `WAR` : `./gradlew bootWar`

Ambos serão salvos no diretório `./build/libs`.

## Execução dos testes

A execução de testes unitários e de integração pode ser efetuada pela IDE escolhida, ou pela linha de comando, no diretório onde estão as fontes:

    ./gradlew test

## Melhorias

Verificar arquivo [TODO.md](TODO.md).

## _Copyright_

Software produzido por Elmon Noronha (<code>elmon dot noronha at gmail dot com</code>).

Licenciado pela [Apache Public License 2.0](LICENSE.md), bem como este <code>README.md</code>.
