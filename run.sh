#!/bin/bash
SERVICE_HOME=/home/jenkins/frame/backend
SERVICE_FILE_NAME=frame-backend.jar
CONFIG_FILE_NAME=frame.properties
LOG_FILE_NAME=frame.log

start() {
    java -jar $SERVICE_HOME/$SERVICE_FILE_NAME --spring.config.location=$SERVICE_HOME/$CONFIG_FILE_NAME >> $SERVICE_HOME/$LOG_FILE_NAME &
}

stop() {
    kill -9 $(pid)
}

pid() {
    pgrep -f $SERVICE_FILE_NAME
}

restart() {
    stop
    start
}

restart
exit 0