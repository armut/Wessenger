#!/bin/bash

mkdir build 2>/dev/null
javac src/*.java src/fenestra/*.java -d build &&
java -classpath .:lib/sqlite-jdbc-3.21.0.jar:build Main

