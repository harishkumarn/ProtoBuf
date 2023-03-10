#!/bin/bash
a=$(pwd)/conf/
docker container run --name proxy --network host   -v $a:/etc/nginx/conf.d --rm nginx:1.15-alpine
