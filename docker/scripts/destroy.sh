#!/bin/bash
set -e

docker-compose down --volumes
docker rmi dockerpublishtest-web dockerpublishtest-nginx
