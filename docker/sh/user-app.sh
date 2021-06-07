#!/usr/bin/env bash

docker rm -f sc-user-app
docker image rm cike/user-app:0.0.1-SNAPSHOT

cd ..
cd user-app
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.user-app.yml up



