#!/bin/bash


# Install curl
sudo apt install  curl

echo "Install zsh :"
sudo apt install zsh

echo "set zsh as default sheel:"
chsh "$(command -v zsh)"

#chsh -s $(which zsh)