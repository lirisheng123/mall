#!/usr/bin/env bash
docker stop sc-user-service
docker stop  sc-gateway
docker stop sc-micro-oauth2-auth
docker stop  sc-mall-search
#docker stop sc-activity-service
docker stop sc-recommend-service
docker stop sc-second-service