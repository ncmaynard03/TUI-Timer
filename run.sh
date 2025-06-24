#!/bin/bash

set -e 

BUILD=false

for arg in "$@"; do 
	if [[ "$arg" == "--build" || "$arg" == "-b" ]]; then 
		BUILD=true
	fi
done

if [[ "$BUILD" == true ]]; then 
	echo "Compiling project..."
	mvn clean compile
fi

echo "Running timer app..." 
mvn exec:java -Dexec.mainClass="com.ncmay.Main"

