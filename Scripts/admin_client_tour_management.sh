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

PATHSEP=':'

VLIBTOUR=${HOME}/.m2/repository/eu/telecomsudparis/vlibtour/

TOURMANAGEMENTENTITY=${VLIBTOUR}/vlibtour-tour-management/vlibtour-tour-management-entity/1.0-SNAPSHOT/vlibtour-tour-management-entity-1.0-SNAPSHOT.jar
TOURMANAGEMENTAPI=${VLIBTOUR}/vlibtour-tour-management/vlibtour-tour-management-api/1.0-SNAPSHOT/vlibtour-tour-management-api-1.0-SNAPSHOT.jar

ADMINCLIENT=${VLIBTOUR}/vlibtour-client/vlibtour-admin-client-tour-management/1.0-SNAPSHOT/vlibtour-admin-client-tour-management-1.0-SNAPSHOT.jar

CLIENTAPI=${VLIBTOUR}/vlibtour-client/vlibtour-client-api/1.0-SNAPSHOT/vlibtour-client-api-1.0-SNAPSHOT.jar

CLASSPATH=${CLASSPATH}${PATHSEP}${TOURMANAGEMENTENTITY}${PATHSEP}${TOURMANAGEMENTAPI}${PATHSEP}${ADMINCLIENT}${PATHSEP}${PATHSEP}${CLIENTAPI}

CLASS=vlibtour.vlibtour_admin_client.VlibTourTourManagementAdminClient

# Start the client
CMD="java -cp ${CLASSPATH} ${CLASS} ${ARGS}"

$CMD
