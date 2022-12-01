#!/bin/bash

REPOSITORY=/home/ec2-user/app/docker_empty
PROJECT_NAME=dockerpublishtest
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

# echo "> Copy build file"
# cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY

# echo "> Destroy docker container running now"
# cd $REPOSITORY/$SCRIPT_DIR
# sh ./destroy.sh

echo "> Deploy new application"
cd $REPOSITORY/$SCRIPT_DIR
sh ./create.sh
# echo "> Check application pid running now"
# CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

# echo "application pid running now: $CURRENT_PID"

# if [ -z "$CURRENT_PID" ]; then
#         echo "> not terminated because application running is not exist"
# else
#         echo "> kill -15 $CURRENT_PID"
#         kill -15 $CURRENT_PID
#         sleep 5
# fi

# echo "> Deploy new application"
# JAR_NAME=$(ls -ti $REPOSITORY/$PROJECT_NAME/$JAR_DIR/*.jar | tail -n 1)

# echo "> JAR Name: $JAR_NAME"
# echo "> RUN $JAR_NAME"
# nohup java -jar \
#    $REPOSITORY/$PROJECT_NAME/$JAR_DIR/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
