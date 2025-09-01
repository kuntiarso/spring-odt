#!/bin/bash

# ******************************************************
# Podman script for running or stopping rabbitmq local service
echo "Podman script for running or stopping rabbitmq local service"


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
CONTAINER_NAME="springodt-rabbitmq-local"
VOLUME_NAME="springodt-rabbitmq-local-data"
RABBITMQ_USERNAME="appuser"
RABBITMQ_PASSWORD="$RABBITMQ_LOCAL_PASSWORD"
RABBITMQ_IMAGE="bitnami/rabbitmq:4.1.3"


# ******************************
# Check if input action is valid
if [ "$#" -ne 1 ]; then
  debug "Usage: $0 {start|stop}"
  exit 1
fi


# *******************************
# Function to start the container
start_container() {
  if podman ps -a | grep -q "$CONTAINER_NAME"; then
    debug "Rabbitmq container '$CONTAINER_NAME' is already running or exists"
    warn "Use $0 stop to stop it, or podman restart $CONTAINER_NAME"
    return 1
  fi

  if ! podman volume exists "$VOLUME_NAME"; then
    debug "Creating volume '$VOLUME_NAME'..."
    podman volume create "$VOLUME_NAME"
    if [ $? -ne 0 ]; then
      error "Failed to create volume '$VOLUME_NAME'"
      return 1
    fi
  else
      debug "Volume '$VOLUME_NAME' already exists"
  fi

  debug "Starting rabbitmq container '$CONTAINER_NAME'..."
  podman run --name "$CONTAINER_NAME" \
    -p 5672:5672 \
    -p 15672:15672 \
    -e "RABBITMQ_USERNAME=$RABBITMQ_USERNAME" \
    -e "RABBITMQ_PASSWORD=$RABBITMQ_PASSWORD" \
    -e "RABBITMQ_MANAGEMENT_ALLOW_WEB_ACCESS=true" \
    -v "$VOLUME_NAME":/bitnami/rabbitmq \
    -d "$RABBITMQ_IMAGE"

  if [ $? -eq 0 ]; then
    debug "Rabbitmq container '$CONTAINER_NAME' started successfully"
    info "You can connect to rabbitmq using management ui: hostname=http://localhost:15672, username=$RABBITMQ_USERNAME password=$RABBITMQ_PASSWORD"
    info "To connect with rabbitmq-cli: podman exec -it $CONTAINER_NAME bash"
    info "Then inside rabbitmq-cli: rabbitmqctl list_users"
  else
    error "Failed to start rabbitmq container '$CONTAINER_NAME'"
    return 1
  fi
}


# ******************************
# Function to stop the container
stop_container() {
  if ! podman ps | grep -q "$CONTAINER_NAME"; then
    debug "Rabbitmq container '$CONTAINER_NAME' is not running"
    debug "Use podman ps -a to see stopped containers"
    return 0
  fi

  debug "Stopping rabbitmq container '$CONTAINER_NAME'..."
  podman stop "$CONTAINER_NAME"

  if [ $? -eq 0 ]; then
    debug "Rabbitmq container '$CONTAINER_NAME' stopped successfully"
  else
    error "Failed to stop rabbitmq container '$CONTAINER_NAME'"
    return 1
  fi
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