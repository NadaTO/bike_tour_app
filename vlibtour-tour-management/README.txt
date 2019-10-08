Only one glassfish instance (standalone or embedded) should be running in the
same domain. So, if you have started a standalone glassfish server, stop it
first:
$ (. java8;asadmin stop-domain)

To compile and install, execute the following command:
$ (. java8;mvn clean install)
Then, the EJB bean can be deployed with the Glassfish asadmin tool.
$ (. java8;asadmin start-domain domain1; asadmin start-database; asadmin deploy vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar)

(. java8;cd vlibtour-tour-management-client/; java -classpath $CLASSPATH:../vlibtour-tour-management-bean/target/vlibtour-tour-management-bean.jar:../vlibtour-tour-management-bean-api/target/vlibtour-tour-management-bean-api-4.0-SNAPSHOT.jar:target/vlibtour-tour-management-bean-client-4.0-SNAPSHOT.jar vlibtour/vlibtour_admin_client/VlibTourTourManagementAdminClient)

(. java8;asadmin undeploy vlibtour-tour-management-bean; asadmin stop-database; asadmin stop-domain domain1)

