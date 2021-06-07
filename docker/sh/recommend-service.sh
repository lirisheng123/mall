#!/usr/bin/env bash
docker rm -f sc-recommend-service
docker image rm cike/recommend-service:0.0.1-SNAPSHOT

cd ..
cd recommend-service

mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.recommend-service.yml up
