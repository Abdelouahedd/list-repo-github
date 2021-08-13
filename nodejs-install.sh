#!/bin/bash

[[ $(id -u) != 0 ]] && { echo "Run as root"; exit 1; }

echo "Dowloand the repository : "
curl -sL https://deb.nodesource.com/setup_14.x | sudo bash -
echo "show the repository added : "
cat /etc/apt/sources.list.d/nodesource.list
echo "run install nodejs 14 :"
apt -y install nodejs
echo "node version installed --> $(node -v)"

echo "install yarn :"
npm install -g yarn

npm install -g npm
sudo chown -R $(whoami) $(npm config get prefix)/{lib/node_modules,bin,share}

echo "install angular 11"
sudo npm install -g @angular/cli@11.0.5