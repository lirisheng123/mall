#!/usr/bin/env bash

docker rm -f sc-micro-oauth2-auth
docker image rm cike/micro-oauth2-auth:1.0.0

cd ..
cd micro-oauth2-auth
pwd
mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.micro-oauth2-auth.yml up



