#!/bin/bash

# the calling script already calls this script by sourcing
#. "$(cd $(dirname "$0") && pwd)"/utils.sh

ARGS=$*

CLASSPATH=${CLASSPATH}${PATHSEP}${VISITCOMMON}${PATHSEP}${GSON}${PATHSEP}${GEOCALC}${PATHSEP}${LOG4J}${PATHSEP}${EMULATIONVISITSERVER}${PATHSEP}${JERSEYHTTP}${PATHSEP}${JERSEYJSON}${PATHSEP}

CLASS=vlibtour.vlibtour_emulation_visit.VisitEmulationServer

# Start the client
CMD="java -cp ${CLASSPATH} ${CLASS} ${ARGS}"

# this script is launched by sourcing => & and export
$CMD &
export VISITEMULATIONSERVER=$!
