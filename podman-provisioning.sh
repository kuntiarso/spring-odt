#!/bin/bash

# ******************************************************
# Podman script for running or stopping all local service
echo "Podman script for running or stopping all local service"


# *************************************
# Exit immediately if any command fails
set -e
set -o pipefail


# *******************
# Logging color funcs
debug() { echo -e "\033[1;30m[DEBUG]   $1\033[0m"; }
info() { echo -e "\033[0;36m[INFO]    $1\033[0m"; }
warn() { echo -e "\033[0;33m[WARN]    $1\033[0m"; }
error() { echo -e "\033[0;31m[ERROR]   $1\033[0m"; }


# ************************************
# Load environment variables from .env 
if [ -f ".env" ]; then
  debug "Loading environment variables from .env..."
  set -o allexport; source .env; set +o allexport
else
  warn "No .env file found in current directory"
fi


# ********************************
# Constant variables for local use
MYSQL_CONTAINER_NAME="springodt-mysql-local"
REDIS_CONTAINER_NAME="springodt-redis-local"
RABBITMQ_CONTAINER_NAME="springodt-rabbitmq-local"


# ******************************
# Check if input action is valid
if [ "$#" -ne 1 ]; then
  debug "Usage: $0 {start|stop}"
  exit 1
fi


# *******************************
# Function to start the container
start_container() {
  debug "Starting mysql container '$MYSQL_CONTAINER_NAME'..."
  podman start "$MYSQL_CONTAINER_NAME"
  debug "Starting redis container '$REDIS_CONTAINER_NAME'..."
  podman start "$REDIS_CONTAINER_NAME"
  debug "Starting rabbitmq container '$RABBITMQ_CONTAINER_NAME'..."
  podman start "$RABBITMQ_CONTAINER_NAME"
}


# ******************************
# Function to stop the container
stop_container() {
  debug "Stopping mysql container '$MYSQL_CONTAINER_NAME'..."
  podman stop "$MYSQL_CONTAINER_NAME"
  debug "Stopping redis container '$REDIS_CONTAINER_NAME'..."
  podman stop "$REDIS_CONTAINER_NAME"
  debug "Stopping rabbitmq container '$RABBITMQ_CONTAINER_NAME'..."
  podman stop "$RABBITMQ_CONTAINER_NAME"
}


# *********************************
# Run command based on input action
case "$1" in
  start)
    start_container
    exit $?
    ;;
  stop)
    stop_container
    exit $?
    ;;
  *)
    error "Invalid argument: $1"
    warn "Usage: $0 {start|stop}"
    exit 1
    ;;
esac