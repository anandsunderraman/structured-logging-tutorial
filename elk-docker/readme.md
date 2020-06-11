# Running ELK with filebeat on local

## Installation

1. Install docker
2. Pull docker images for elasticsearch, kibana, logstash
3. install filebeat locally

### Install Kibana with plugin
docker build -t kibana6.6.0-enhancetable .

## Running ELK
1. docker-compose up

## Running Filebeat
1. ./filebeat -e -c filebeat.yml -d "publish"

## Navigate to Kibana
1. http://localhost:5601/ 