#!/bin/bash

# killall java # to think about the possibility of processes from old executions!

# test Java 8
if ! $(java -version 2>&1 | head -1 | grep '\"1\.8' > /dev/null); then
    echo "Not a JAVA 8 version: '$(java -version 2>&1 | head -1)'"
    exit 1
fi

# clean up the domain and the database, start the domain and the database, and deploy the tour management bean
if [ -n "$GLASSFISH_HOME" ]; then
    asadmin undeploy vlibtour-tour-management-bean
    asadmin stop-database
    asadmin stop-domain domain1
    asadmin start-domain domain1
    asadmin start-database
    asadmin deploy vlibtour-tour-management/vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar
fi

# populate the database with the POIs and the tours
if [ -z "$GLASSFISH_HOME" ]; then
    echo "Glassfish is not installed: the client to populate the database with the POIs and the tours cannot be executed"
else
    ./Scripts/admin_client_tour_management.sh populate toursAndPOIs
fi

# clean up the rabbitmq server, and start the rabbitmq server
if [ -n "$RABBITMQ_MNESIA_BASE" ]; then
    rabbitmqctl stop
    rabbitmq-server -detached
    rabbitmqctl stop_app
    rabbitmqctl reset
    rabbitmqctl start_app
fi

# start the lobby room server
if [ -z "$RABBITMQ_MNESIA_BASE" ]; then
    echo "RabbitMQ is not installed: the lobby room server cannot be executed"
else
    ./Scripts/start_lobby_room_server.sh
    sleep 3
fi

# start the visit emulation server
if [ -z "$GLASSFISH_HOME" ]; then
    echo "Glassfish is not installed: the visit emulation server cannot be executed"
else
    procNumber="$(netstat -nlp | grep 127.0.0.1:8888 | cut -d"N" -f2 | cut -d"/" -f1)"
    if [ -n "$procNumber" ]; then
        echo "There is an old visit emulation server running; remove proc $procNumber"
        kill -9 "$procNumber"
    fi
    ./Scripts/start_visit_emulation_server.sh
    sleep 3
fi

# start the tourist applications
if [ -z "$GLASSFISH_HOME" ] || [ -z "$RABBITMQ_MNESIA_BASE" ]; then
    echo "Glassfish or RabbitMQ are not installed: the tourist application cannot be executed"
else
    ./Scripts/start_tourist_application_w_emulated_location.sh Joe
    JOE=$TOURISTAPPLICATION
    sleep 1
    ./Scripts/start_tourist_application_w_emulated_location.sh Avrel
    AVREL=$TOURISTAPPLICATION
    sleep 1
fi

echo "Hit return to end the demonstration"
read x

# stop the tourist applications, just in case
if [ -n "$JOE" ]; then
    kill -9 $JOE
fi
if [ -n "$AVREL" ]; then
    kill -9 $AVREL
fi
# stop the lobby room server
if [ -n "$LOBBYROOMSERVER" ]; then
    kill -9 $LOBBYROOMSERVER
fi
# stop the visit emulation server
if [ -n "$VISITEMULATIONSERVER" ]; then
    kill -9 $VISITEMULATIONSERVER
fi

# stop the rabbitmq server
if [ -n "$RABBITMQ_MNESIA_BASE" ]; then
    rabbitmqctl stop_app
    rabbitmqctl stop
fi

# empty the database, undeploy the bean, and stop the database and the domain
if [ -n "$GLASSFISH_HOME" ]; then
    ./Scripts/admin_client_tour_management.sh empty toursAndPOIs
    asadmin undeploy vlibtour-tour-management-bean
    asadmin stop-database
    asadmin stop-domain domain1
fi
