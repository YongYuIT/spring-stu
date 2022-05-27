package hello;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

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

    @Data
    public static class HelloResponse {
        int respCode = 200;
        String respMsg;
    }
}
