import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;

public class TestLimit {
    @Test
    public void reqTestMultiple() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reqTestMultiple("yong", "M", "cn");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reqTestMultiple("guo", "F", "cn");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reqTestMultiple("king", "F", "uk");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reqTestMultiple("li", "N", "us");
            }
        }).start();
        try {
            Thread.sleep(20 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reqTestMultiple(String name, String gen, String con) {
        Instant start = Instant.now();
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String result = null;
            try {
                result = restTemplate.getForObject("http://0.0.0.0:8080/gateway/multiple?name=" + name + "&gen=" + gen + "&con=" + con, String.class);
                System.out.println("result-->" + Instant.now() + "-->" + i + "-->" + name + "-->" + gen + "-->" + con + "-->" + result);

            } catch (Exception ex) {
                System.out.println("result--> failed -->" + Instant.now() + "-->" + i + "-->" + name + "-->" + gen + "-->" + con);
            }
        }
        System.out.println("all time-->" + name + "-->" + gen + "-->" + con + "-->" + Duration.between(start, Instant.now()).toMillis());
    }
}
