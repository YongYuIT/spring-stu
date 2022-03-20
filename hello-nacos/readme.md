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

## gradle dep

~~~groovy
    implementation group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.2.7.RELEASE'
    implementation group: 'com.alibaba.cloud', name: 'spring-cloud-starter-alibaba-nacos-discovery', version: '2.2.7.RELEASE'
    implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-openfeign', version: '2.2.7.RELEASE'
~~~

***version of spring-boot-starter-web and spring-cloud-starter-alibaba-nacos-discovery and spring-cloud-starter-openfeign should be the same***

## application.yml

~~~yaml
spring:
  application:
    name: test-hello-cli

nacos:
  discovery:
    server-addr: 127.0.0.1:8848
    auto-register: true

server:
  port: 8081
~~~

## mark on main class

~~~java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application
~~~

## create an interface marked by @FeignClient

~~~java
@FeignClient("test-hello-svc")
public interface HelloInterface {
    @RequestMapping(method = RequestMethod.GET, value = "/yong-test/hello-api/sayhello/yong")
    String sayHello();
}
~~~

and call this interface obj

~~~java
@Autowired
HelloInterface helloInterface;
... ...
helloInterface.sayHello();
~~~



