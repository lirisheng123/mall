#!/usr/bin/env bash


docker rm -f sc-sentinel

docker image rm sentinel:01

cd dockerfile

docker build -f sentinelDashBoard -t sentinel:01 .

cd ..
cd docker-compose

docker-compose -f docker-compose.sentinel.yml up

#
#docker rm -f sc-sentinel
#
#docker image rm cike/sentinel-dashboard-1.7.2
#
#cd ..
#pwd
#cd  sentinel-dashboard/sentinel-dashboard
#
#mvn clean package -DskipTests && mvn docker:build
#
#cd ..
#cd ..
#cd docker/docker-compose
#
##cd docker-compose
#
#docker-compose -f docker-compose.sentinel.yml up