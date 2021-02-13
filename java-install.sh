#!/bin/bash

[[ $(id -u) != 0 ]] && { echo "Run as root"; exit 1; }

echo "JDK 11 --> run"
apt-get install openjdk-11-jdk

echo "Maven 3.6 --> run"
apt install maven:3.6/default

echo "Do NOT forget to install Intellij IDEA"
