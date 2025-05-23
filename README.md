![Performance - Microservice Template](docs/microservice-template.png)

Existe as classes com o sufixo ou prefixo "example" onde vai ser renomeada conforme o projeto.

## How to install
* [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
* Gradle `sdk install gradle 7.4`
* Mongodb `use docker`

## Dependencias
`./gradlew dependencies`
* JERSEY
* SPRING
* KOTLIN
* RETROFIT
* METRICS
* LOG4J
* RABBITMQ

## RabbitMQ
[docker-compose.txt](https://github.com/aurumsoftware/aesir/files/8465207/docker-compose.txt)
* Mudar a extensão do docker-compose para yml
* Entrar na pasta onde esta o arquivo e executar **docker-compose up**
* Acessar http://localhost:15672/, user = guest, password = guest

## Subindo o server
`./gradlew build && ./gradlew :root-project:bootRun` 


## Cloning project
* Copy/fork this project;
* Create build files  (kube, drone, dockerfiles...)  with devops
