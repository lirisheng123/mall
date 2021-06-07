#!/usr/bin/env bash

docker rm -f sc-activity-service
docker image rm cike/activity-service:0.0.1-SNAPSHOT

cd ..
cd activity-service
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.activity-service.yml up



