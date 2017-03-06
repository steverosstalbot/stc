#!/bin/sh

# Directory containing fsm jars
LIB=lib

PRG=`basename "$0"`
DIR=`dirname "$0"`   # Non Cygwin..
#DIR=`dirname "$0"`/.. # Cygwin
#BASEDIR=$(dirname $0)

usage() {
  echo Usage:  'fsm.sh name [client|server] ...'
  #echo Usage:  'fsm.sh fsmconfig ipconfig [client|server] ...'
  cat <<EOF
  
 <fsmonfig.txt>     Source config file for easyFSM 
 <ipconfig.txt>     FakeDNS
 <client|server>    Playing the role of client or server
  
 Options: 
EOF
}

fixpath() {
  windows=0

  if [ `uname | grep -c CYGWIN` -ne 0 ]; then
    windows=1
  fi

  cp="$1"
  if [ "$windows" = 1 ]; then
      cygpath -pw "$cp"
  else
      echo "$cp"
  fi
}

ARGS=

CLASSPATH=''$DIR'/'$LIB'/fsm-server.jar'
CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/EasyFSM.jar'
CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/EasyFSM-0.0.006.jar'
CLASSPATH=$CLASSPATH':'$DIR'/'$LIB'/log4j-1.2.8.jar'
CLASSPATH="'"`fixpath "$CLASSPATH"`"'"

usage=0

while true; do
    case "$1" in
        "")
            break
            ;;
        -h)
            usage=1
            break
            ;;
        --help)
            usage=1
            break
            ;;
        *)
            ARGS="$ARGS '$1'"
            shift
            ;;
    esac
done

if [ "$usage" = 1 ]; then
  usage
  exit 0
fi

CMD='java -cp '$CLASSPATH' com.fsm.example.SocketProgram' $0 generated/$0_config.txt ipconfig.txt $1
echo $CMD

fsmc() {
  eval $CMD "$@"
}

if [ "$verbose" = 1 ]; then
  echo $CMD "$ARGS"
fi

fsmc "$ARGS"

