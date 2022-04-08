import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;

public class TestLimit {
    @Test
    public void reqTestMultiple() {
        Instant start = Instant.now();
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String result = restTemplate.getForObject("http://0.0.0.0:8080/gateway/multiple?name=yong&gen=M", String.class);
            System.out.println("result-->" + i + "-->" + result);

        }
        System.out.println("all time-->" + Duration.between(start, Instant.now()).toMillis());
    }
}
