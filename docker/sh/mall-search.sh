#!/usr/bin/env bash

docker rm -f sc-mall-search
docker image rm cike/mall-search:1.0.0

cd ..
cd mall-search
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.mall-search.yml up



