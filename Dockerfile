FROM alpine:latest

EXPOSE 8080

ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin

ENV TOMCAT_VERSION_MAJOR 8
ENV TOMCAT_VERSION_FULL  8.5.14
ENV CATALINA_HOME /opt/tomcat

RUN apk --update add openjdk8-jre &&\
    mkdir -p /opt/jdk &&\
    ln -s /usr/lib/jvm/java-1.8-openjdk/bin /opt/jdk

RUN apk add --update curl &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  md5sum -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  gunzip -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz | tar -xf - -C /opt &&\
  rm -f apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.md5 &&\
  ln -s /opt/apache-tomcat-${TOMCAT_VERSION_FULL} /opt/tomcat &&\
  rm -rf /opt/tomcat/webapps/examples /opt/tomcat/webapps/docs &&\
  apk del curl &&\
  rm -rf /var/cache/apk/*

CMD ${CATALINA_HOME}/bin/catalina.sh run

COPY ./target/gerenciamento-eletronico-imagens-2.1.2.RELEASE.war /opt/apache-tomcat-8.5.14/webapps/gerenciamento-eletronico-imagens-2.1.2.RELEASE.war
RUN mkdir -p /home/tmp/ && chmod 777 /home/tmp/ -R
