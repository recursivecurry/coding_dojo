#!/bin/bash
TARGET_NAME=`basename ${PWD}/$1`
cd $1
if [ -e ${TARGET_NAME} ]
then
	rm ${TARGET_NAME}.out
fi
c++ ${TARGET_NAME}.c* -o ${TARGET_NAME}.out
grep "|" <(cat -n <(diff -B -y <(./${TARGET_NAME}.out < ${TARGET_NAME}.in) <(cat ${TARGET_NAME}.exp)))
cd ${PWD}
