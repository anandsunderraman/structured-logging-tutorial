# ELK & Structured Logging Tutorial

This repository has all the artifacts needed to run a demo of ELK with the sample spring boot apps

##  Repo Structure

### orders

Spring boot application that runs the orders microservice

### shipping

Spring boot application that runs the shipping microservice

### elk-docker

The docker-compose.yml starts elasticsearch, logstash and kibana

### elk-docker/filebeat-conf

This contains the configurations for filebeat to source logs from the orders and shipping microservices

## Pre-requisites

1. Docker
2. Java 8
3. Filebeat 6.6 (https://www.elastic.co/guide/en/beats/filebeat/6.6/filebeat-installation.html)
4. Configure filebeat
   Replace contents of filebeat.yml with that in elk-docker/filebeat-conf/filebeat.yml
5. Configure filebeat to read logs from the apps
   Modify the elk-docker/filebeat-conf/orders.yml and elk-docker/filebeat-conf/shipping.yml to point to the location of the logs
6. Copy elk-docker/filebeat-conf/orders.yml and elk-docker/filebeat-conf/shipping.yml to the
   conf folder inside filebeat directory

## Steps to run the application

1. Start docker
2. Start the elk stack, navigate to the elk-docker folder
```
docker-compose up
```
3. Start filebeat
```
./filebeat -e -c filebeat.yml -d "publish"
```
4. Start the orders application, navigate to the orders app folder
```
mvn spring-boot:run 
```
5. Start the shipping application, navigate to the shipping app folder
```
mvn spring-boot:run -Dserver.port=9000
```
6. Login to kibana at http://localhost:5601
7. Import indexes and other ELK config using elk-configs/elkSavedObjects.json

