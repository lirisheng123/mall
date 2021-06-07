#!/usr/bin/env bash

docker rm -f sc-orderAdmin-app
docker image rm cike/orderAdmin-app:0.0.1-SNAPSHOT

cd ..
cd orderAdmin-app
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.orderAdmin-app.yml up



