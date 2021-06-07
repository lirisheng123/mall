#!/usr/bin/env bash

docker rm -f zipkin


cd docker-compose

docker-compose -f docker-compose.zipkin.yml up