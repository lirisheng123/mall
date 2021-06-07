#!/usr/bin/env bash

docker rm -f sc-monitor
docker image rm cike/monitor:0.0.1-SNAPSHOT

cd ..
cd  monitor

mvn package && mvn docker:build

cd ..
cd docker/docker-compose

docker-compose  -f docker-compose.monitor.yml up



