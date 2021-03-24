# start ZK with docker 

~~~bash
docker run --name zookeeper-1 --restart always -d -p 2181:2181 zookeeper
~~~

# start 2 proc

build --> buildDependents

java -jar build/libs/hello-elasticjob-0.0.1-SNAPSHOT.jar

java -jar build/libs/hello-elasticjob-0.0.1-SNAPSHOT.jar

java -jar build/libs/hello-elasticjob-0.0.1-SNAPSHOT.jar

开启三个进程，可以看到这三个进程会自行分配任务