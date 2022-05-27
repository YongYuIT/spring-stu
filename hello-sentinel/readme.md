
# 下载可执行jar包

~~~jshelllanguage
wget https://github.com/alibaba/Sentinel/releases/download/1.8.4/sentinel-dashboard-1.8.4.jar
~~~

# 运行jar

~~~jshelllanguage
java -jar sentinel-dashboard-1.8.4.jar
~~~

* visit 0.0.0.0:8080
* username/pwd: sentinel/sentinel



# Create Spring-boot proj

* add gradle dependency

~~~groovy
implementation group: 'com.alibaba.cloud', name: 'spring-cloud-starter-alibaba-sentinel', version: '2021.0.1.0'
~~~

* add config to application.yml

~~~
spring:
  cloud:
    sentinel:
      transport:
        dashboard: 127.0.0.1:8080
        port: 8719
~~~

# 新增降级规则

# ref to

* https://sentinelguard.io
