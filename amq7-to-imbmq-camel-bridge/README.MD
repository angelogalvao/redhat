This project must run inside the JBoss A-MQ 7.0 product.

How to install this project.

1. Build this project (mvn clean package)
2. Copy de file to ARTEMIS_HOME/web
3. Edit the ARTEMIS_INSTANCE_HOME/etc/bootstrap.xml and adds the new APP as the example above:

	<web bind="http://localhost:8161" path="web">
		.. 
       *<app url="amq7-to-imbmq-camel-bridge" war="amq7-to-imbmq-camel-bridge-1.0.0.war"/>*
   </web> 
4. Copy the bridde.properties file to ARTEMIS_INSTANCE_HOME/etc/ and edit change the parameters to attend your needs.
5. Copy the bridges.xml file to ARTEMIS_INSTANCE_HOME/etc/bridges (must be created) to configure the dinamics routes.


Now just send the messages to the "from" queue and se the magic happen. :)