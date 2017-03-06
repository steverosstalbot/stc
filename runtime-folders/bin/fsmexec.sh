#!/bin/sh

# Directory containing fsm jars
NAME=$1
ROLE=$2
FOLDER=$3
DELAY=$4

LIB=lib

PRG=`basename "$0"`
DIR=`dirname "$0"`   # Non Cygwin..

ARGS=

CLASSPATH=''$DIR'/'$LIB'/fsm.jar'
CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/EasyFSM-1.1.0.jar'
#CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/EasyFSM-0.0.006.jar'
CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/log4j-1.2.8.jar'

CMD='java -cp '$CLASSPATH' com.fsm.example.SocketProgram' 
echo $CMD ${NAME} ${FOLDER}/${NAME}_config.txt ipconfig.txt ${ROLE} ${DELAY}
$CMD ${NAME} ${FOLDER}/${NAME}_config.txt ipconfig.txt ${ROLE} ${DELAY}

