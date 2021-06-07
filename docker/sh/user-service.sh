#!/usr/bin/env bash

docker rm -f sc-user-service
docker image rm cike/user-service:0.0.1-SNAPSHOT

cd ..
cd user-service
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.user-service.yml up



