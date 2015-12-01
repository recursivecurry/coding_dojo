cmake_minimum_required(VERSION 3.2)
project(euler002)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES euler002.cpp)
add_executable(euler002 ${SOURCE_FILES})