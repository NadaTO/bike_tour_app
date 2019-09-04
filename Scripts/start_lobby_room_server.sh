#!/bin/bash

# the calling script already calls this script by sourcing
#. "$(cd $(dirname "$0") && pwd)"/utils.sh

ARGS=$*

CLASSPATH=${CLASSPATH}${PATHSEP}${AMQPCLIENT}${PATHSEP}${SL4J}${PATHSEP}${SL4JSIMPLE}${PATHSEP}${COMMONLANG3}${PATHSEP}${COMMONLOGGING}${PATHSEP}${LOG4J}${PATHSEP}${PATHSEP}${LOBBYROOMAPI}${PATHSEP}${LOBBYROOMSERVER}${PATHSEP}${UTILPASSAY}${PATHSEP}

CLASS=vlibtour.vlibtour_lobby_room_server.VLibTourLobbyServer

# Start the client
CMD="java -cp ${CLASSPATH} ${CLASS} ${ARGS}"

# this script is launched by sourcing => & and export
$CMD &
export LOBBYROOMSERVER=$!
