cmake_minimum_required(VERSION 3.2)
project(the_time_in_words)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES the-time-in-words.cpp)
add_executable(the_time_in_words ${SOURCE_FILES})