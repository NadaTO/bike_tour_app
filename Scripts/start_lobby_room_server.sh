#!/bin/bash

# test Java 8
if ! $(java -version 2>&1 | head -1 | grep '\"1\.8' > /dev/null); then
    echo "Not a JAVA 8 version: '$(java -version 2>&1 | head -1)'"
    return 1
fi

# configure variables
if [ -e utils.sh ]; then
    . ./utils.sh
else
    if [ -d Scripts ]; then
        . ./Scripts/utils.sh
    else
        echo "Don't where you are"
        return 1
    fi
fi

ARGS=$*

CLASSPATH=${CLASSPATH}${PATHSEP}${AMQPCLIENT}${PATHSEP}${SL4J}${PATHSEP}${SL4JSIMPLE}${PATHSEP}${COMMONLANG3}${PATHSEP}${COMMONLOGGING}${PATHSEP}${LOG4J}${PATHSEP}${PATHSEP}${LOBBYROOMAPI}${PATHSEP}${LOBBYROOMSERVER}${PATHSEP}${UTILPASSAY}${PATHSEP}

CLASS=vlibtour.vlibtour_lobby_room_server.VLibTourLobbyServer

# Start the client
CMD="java -cp ${CLASSPATH} ${CLASS} ${ARGS}"

# this script is launched by sourcing => & and export
$CMD &
export PROCLOBBYROOMSERVER=$!
