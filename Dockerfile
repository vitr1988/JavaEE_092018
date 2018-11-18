FROM airhacks/glassfish:v5
#docker network create -d bridge --subnet 192.168.0.0/24 --gateway 192.168.0.1 dockernet
#use flag --net=dockernet to connect to docker host by ip 192.168.0.1 (for Oracle DBMS)
#RUN asadmin --user=admin stop-domain && \
#    echo "--- Setup the password file ---" && \
#    echo "AS_ADMIN_PASSWORD=" > /tmp/glassfishpwd && \
#    echo "AS_ADMIN_NEWPASSWORD=changeme" >> /tmp/glassfishpwd  && \
#    echo "--- Enable DAS, change admin password, and secure admin access ---" && \
#    asadmin --user=admin --passwordfile=/tmp/glassfishpwd change-admin-password && \
#    asadmin start-domain && \
#    echo "AS_ADMIN_PASSWORD=changeme" > /tmp/glassfishpwd && \
#    asadmin --user=admin --passwordfile=/tmp/glassfishpwd enable-secure-admin && \
#    asadmin --user=admin stop-domain && \
#    asadmin start-domain --verbose && \
#    rm /tmp/glassfishpwd

COPY lib/ojdbc6-11.2.0.4.jar $GLASSFISH_HOME/lib/ojdbc6-11.2.0.4.jar
COPY config/domain.xml $GLASSFISH_HOME/domains/domain1/config/domain.xml

COPY target/JavaEE.war $DEPLOYMENT_DIR/JavaEE.war

#FROM payara/server-full
#COPY target/JavaEE.war $AUTODEPLOY_DIR/JavaEE.war