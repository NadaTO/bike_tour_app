#!/bin/bash

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
