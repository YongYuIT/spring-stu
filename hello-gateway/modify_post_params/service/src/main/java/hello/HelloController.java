package hello;

import lombok.Data;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("hello-api")
public class HelloController {
    @GetMapping(value = "/sayhello/{name}")
    public HelloResponse sayHello(@PathVariable("name") String name) {
        HelloResponse response = new HelloResponse();
        response.setRespMsg("hello --> " + name);
        return response;
    }

    @GetMapping(value = "/hello")
    public HelloResponse hello() {
        HelloResponse response = new HelloResponse();
        response.setRespMsg("hello");
        return response;
    }

    @PostMapping(value = "/test_post_modify")
    public HelloResponse TestPostModify(@RequestBody HelloRequest helloRequest) {
        HelloResponse response = new HelloResponse();
        response.setRespMsg("hello-->" + helloRequest.name + "-->" + helloRequest.age);
        return response;
    }


    @Data
    public static class HelloRequest {
        String name;
        int age;
    }

    @Data
    public static class HelloResponse {
        int respCode = 200;
        String respMsg;
    }
}
