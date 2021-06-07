#!/usr/bin/env bash


docker rm -f kibana elasticsearch

docker image rm elasticsearch:01

cd dockerfile

docker build -f elasticsearchDockerFile -t elasticsearch:01 .

cd ..
cd docker-compose

docker-compose -f docker-comopse.elasticsearch.yml up