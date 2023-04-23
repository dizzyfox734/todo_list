#!/bin/bash
set -e

docker-compose down --volumes
docker rmi todo_list-web todo_list-nginx
