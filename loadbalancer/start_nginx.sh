a=$(pwd)/conf/
docker container run --name proxy   -p 8585:8585 -v $a:/etc/nginx/conf.d --rm nginx
