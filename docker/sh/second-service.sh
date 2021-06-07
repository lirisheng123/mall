#!/usr/bin/env bash
docker rm -f sc-second-service
docker image rm cike/second-service:0.0.1-SNAPSHOT

cd ..
cd second-service

mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.second-service.yml up
