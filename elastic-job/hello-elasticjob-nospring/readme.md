ref to: https://shardingsphere.apache.org/elasticjob

# start ZK with docker 

~~~bash
docker run --name zookeeper-1 --restart always -d -p 2181:2181 zookeeper
~~~

# package and run proc with 3 case

## 通过gradle Application Plugin插件，将工程打成一个可执行包

modify build.gradle file

~~~
plugins {
...
    id 'application' // add gradle Application Plugin
...
}

mainClassName = "com.thinking.hello_elasticjob_nospring.Main" // config with gradle Application Plugin
~~~

check setting.gradle file

~~~
rootProject.name = 'hello-elasticjob-nospring' //ensure this item exists when using gradle Application Plugin
~~~

Tasks --> build --> build

## run proc with 3 case

~~~bash
cd build/distributions
unzip hello-elasticjob-nospring-1.0-SNAPSHOT.zip
bash hello-elasticjob-nospring-1.0-SNAPSHOT/bin/hello-elasticjob-nospring
bash hello-elasticjob-nospring-1.0-SNAPSHOT/bin/hello-elasticjob-nospring
bash hello-elasticjob-nospring-1.0-SNAPSHOT/bin/hello-elasticjob-nospring
~~~
可以看到这三个进程会自行分配任务