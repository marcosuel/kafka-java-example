# Descrição
Configuração para enviar/receber tópicos kafka de forma genérica, sem precisa configurar individualmente cada tópico.

## Como utilizar

* Adicionar a seguinte opção na "vm options": -Dspring.profiles.active=local

* Rodar o comando: docker-compose up

Os tópicos são criados automaticamente.

## Algumas tecnologias

* Java 17
* Spring Kafka - https://spring.io/projects/spring-kafka
* Kafka Avro Serializer - https://mvnrepository.com/artifact/io.confluent/kafka-avro-serializer/5.5.0
