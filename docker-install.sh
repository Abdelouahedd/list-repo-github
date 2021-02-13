#!/bin/bash

set -e

# Install curl
sudo apt install  curl

# Install docker engine
echo "Install docker engine"
curl -fsSl http://get.docker.com -o /tmp/get-docker.sh
sudo sh /tmp/get-docker.sh

# Install docker-compose
echo "Install docker-compose"
sudo apt install  docker-compose

# add user to docker group
echo "add user to docker group"
user=$(whoami)
sudo usermod -aG docker "$user"

#end of script
echo "Docker is installed and the user $user is added to the group"