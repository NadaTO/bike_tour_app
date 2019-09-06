#!/bin/bash

# test Java 8
if ! $(java -version 2>&1 | head -1 | grep '\"1\.8' > /dev/null); then
    echo "Not a JAVA 8 version: '$(java -version 2>&1 | head -1)'"
    exit 1
fi

# killall java # to think about the possibility of processes from old executions!

# clean up the domain and the database
asadmin undeploy vlibtour-tour-management-bean
asadmin stop-database
asadmin stop-domain domain1
# start the domain and the database, and deploy the tour management bean
asadmin start-domain domain1
asadmin start-database
asadmin deploy vlibtour-tour-management/vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar

# source the script and export in the script => definitions usable in the sequel
. ./Scripts/utils.sh

# populate the database with the POIs and the tours
./Scripts/admin_client_tour_management.sh populate toursAndPOIs

# clean up the rabbitmq server
rabbitmqctl stop
# start the rabbitmq server
rabbitmq-server -detached
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app

# start the lobby room server
# source the script and LOBBYROOMSERVER exported in the script
. ./Scripts/start_lobby_room_server.sh
sleep 3

# start the visit emulation server
# source the script and VISITEMULATIONSERVER exported in the script
. ./Scripts/start_visit_emulation_server.sh
sleep 3

# start the tourist applications
# source the script and TOURISTAPPLICATION exported in the script
. ./Scripts/start_tourist_application_w_emulated_location.sh Joe
JOE=$TOURISTAPPLICATION
sleep 1
. ./Scripts/start_tourist_application_w_emulated_location.sh Avrel
AVREL=$TOURISTAPPLICATION

echo "Hit return to end the demonstration"
read x

# stop the tourist application, just in case
kill -9 $JOE
kill -9 $AVREL
# stop the lobby room server
kill -9 $LOBBYROOMSERVER
# stop the visit emulation server
kill -9 $VISITEMULATIONSERVER

# stop the rabbitmq server
rabbitmqctl stop_app
rabbitmqctl stop

# empty the database
./Scripts/admin_client_tour_management.sh empty toursAndPOIs

# undeploy the bean, and stop the database and the domain
asadmin undeploy vlibtour-tour-management-bean
asadmin stop-database
asadmin stop-domain domain1
