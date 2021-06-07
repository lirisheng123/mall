#!/usr/bin/env bash


docker rm -f sc-logstash

cd docker-compose

docker-compose  -f docker-compose.logstash.yml up