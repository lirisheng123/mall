#!/usr/bin/env bash

docker rm -f sc-gateway
docker image rm cike/gateway:0.0.1-SNAPSHOT

cd ..
cd gateway
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.gateway.yml up



