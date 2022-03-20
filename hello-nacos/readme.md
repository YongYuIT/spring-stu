# down load nacos package

https://github.com/alibaba/nacos/releases

and get nacos-server-2.0.4.tar.gz

~~~shell
$ tar -xzvf nacos-server-2.0.4.tar.gz
~~~

***for current spring boot 2.X, must download nacos 2.X version***

# start standalone nacos

~~~shell
$ cd nacos/bin
$ bash startup.sh -m standalone


nacos is starting with standalone
nacos is starting，you can check the /home/yong/Desktop/test_nacos/nacos/logs/start.out

~~~

view browser http://0.0.0.0:8848/nacos

user/pwd: nacos/nacos

# create SpringBoot service proj

## gradle dep

~~~groovy
    implementation 'org.springframework.boot:spring-boot-starter-web:2.6.4'
    implementation group: 'com.alibaba.boot', name: 'nacos-discovery-spring-boot-starter', version: '0.2.10'
~~~

***nacos-discovery-spring-boot-starter 0.2.X version for spring-boot-starter-web 2.X version***

## application.yml

~~~yaml
spring:
  application:
    name: test-hello-svc

nacos:
  discovery:
    server-addr: 127.0.0.1:8848
    auto-register: true

server:
  port: 8080
~~~

# create Springboot client proj(using feign)






