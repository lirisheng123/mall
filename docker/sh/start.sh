#!/usr/bin/env bash
docker start sc-user-service
docker start  sc-gateway
docker start  sc-micro-oauth2-auth
docker start   sc-mall-search
#docker start sc-activity-service
docker start sc-recommend-service
docker start sc-second-service