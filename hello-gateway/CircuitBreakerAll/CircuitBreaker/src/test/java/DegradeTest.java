import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class DegradeTest {
    @Test
    public void doTest() {
        for (int i = 0; i < 1000; i++) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON);
            Map req = new HashMap<>();
            req.put("aaa", "aaaa");
            req.put("bbb", "bbb");
            HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(req), headers);
            String msg = "";
            if (i % 3 < 2) {
                msg = doPost(restTemplate, "http://0.0.0.0:8000/degreade/mock/aaa/bbb/exp", httpEntity);
            } else {
                msg = doPost(restTemplate, "http://0.0.0.0:8000/degreade/mock/aaa/bbb", httpEntity);
            }

            System.out.println("get msg success -->" + msg);
        }
    }

    private String doPost(RestTemplate restTemplate, String url, HttpEntity<String> httpEntity) {
        String msg = "";
        try {
            msg = restTemplate.postForObject(url, httpEntity, String.class);
        } catch (Throwable ex) {
            msg = ex.getLocalizedMessage();
        }
        return msg;
    }
}
