#!/bin/bash


# Install docker engine
sudo apt-get remove docker docker-engine docker.io containerd runc

sudo apt-get update

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

curl -fsSL https://download.docker.com/linux/debian/gpg | sudo apt-key add -

sudo apt-key fingerprint 0EBFCD88

sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"

# Install docker-compose
sudo apt install  docker-compose

# add user to docker group
user=$(whoami)
sudo usermod -aG docker "$user"

#end of script
echo "Docker is installed and the user $user is added to the group"