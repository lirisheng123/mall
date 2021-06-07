#!/usr/bin/env bash

docker rm -f sc-order-service
docker image rm cike/order-service:0.0.1-SNAPSHOT

cd ..
cd order-service
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.order-service.yml up



