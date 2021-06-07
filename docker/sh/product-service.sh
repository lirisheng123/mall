#!/usr/bin/env bash

docker rm -f sc-product-service
docker image rm cike/product-service:0.0.1-SNAPSHOT

cd ..
cd product-service
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.product-service.yml up



