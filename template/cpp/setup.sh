#!/bin/sh
mkdir Release
cd Release
cmake -DCMAKE_BUILD_TYPE=Release -B. -H..
cd ..
mkdir Debug
cd Debug
cmake -DCMAKE_BUILD_TYPE=Debug -B. -H..
cd ..
