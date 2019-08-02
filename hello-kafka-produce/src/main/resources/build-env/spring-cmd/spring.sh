#!/usr/bin/env bash
echo "PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin" >> ~/.bashrc
source ~/.bashrc
cd /spring-proj
$JAVA_HOME/bin/java -jar hello-kafka-produce-0.0.1-SNAPSHOT.jar > /spring-logs/log &
/bin/bash