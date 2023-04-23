#!/bin/bash

REPOSITORY=/home/ec2-user/app/todo_list
PROJECT_NAME=todo_list
JAR_DIR=build/libs
BACKUP_DIR=backup
SCRIPT_DIR=docker/scripts

cd $REPOSITORY/$PROJECT_NAME

echo "> Git Pull"
git pull

echo "> Destroy docker container running now"
cd $REPOSITORY/$SCRIPT_DIR
sh ./destroy.sh

if ! [[ -d $REPOSITORY/$BACKUP_DIR ]]; then
	mkdir $REPOSITORY/$BACKUP_DIR
fi

echo "> Backup previous version"
mv $REPOSITORY/$PROJECT_NAME/build/libs/* $REPOSITORY/$BACKUP_DIR

echo "> Start Building Project"
cd $REPOSITORY/$PROJECT_NAME
./gradlew build

echo "> Deploy new application"
cd $REPOSITORY/$SCRIPT_DIR
sh ./create.sh
