FROM primetoninc/jdk:1.8

VOLUME /tmp

ADD target/*.jar challenger-api.jar

RUN sh -c 'touch /challenger-api.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /challenger-api.jar" ]
