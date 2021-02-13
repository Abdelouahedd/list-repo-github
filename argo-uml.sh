#!/bin/bash
###########################################################
# Script Name	: install-argouml.sh
###########################################################


tarfile="files/ArgoUML-0.35.1.tar.gz"
tarversion="0.35.1"

# Check if file exist
[[ ! -f $tarfile ]] && {
    echo "file files/ArgoUML-0.35.1.tar.gz NOT FOUND";
    exit 1;
}

[[ ! -f "files/argouml.desktop" ]] && {
    echo "files/argouml.desktop NOT FOUND";
    exit 1;
}

# Extract file and create symbolic link
sudo tar -xzf $tarfile -C /usr/src
sudo mv "/usr/src/argouml-${tarversion}" "/usr/local/argouml/"
sudo ln -s "/usr/local/argouml/argouml.sh" "/usr/local/bin/argouml"

# Create .desktop file
sudo cp files/argouml.desktop /usr/share/applications/argouml-${tarversion}.desktop