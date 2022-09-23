import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class RateTest {
    @Test
    public void rateTest() {
        RestTemplate restTemplate = new RestTemplate();
        String pathParams = "aaa";
        for (int i = 0; i < 100; i++) {
            try {
                restTemplate.getForEntity("http://0.0.0.0:8080/diff-gateway/" + pathParams, String.class);
                System.out.println("success for " + pathParams + "-------------------------------------------------------------" + i);
            } catch (Exception exp) {
                System.out.println("failed for " + pathParams + "-------------------------------------------------------------" + i);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        pathParams = "bbb";
        for (int i = 0; i < 100; i++) {
            try {
                restTemplate.getForEntity("http://0.0.0.0:8080/diff-gateway/" + pathParams, String.class);
                System.out.println("success for " + pathParams + "-------------------------------------------------------------" + i);
            } catch (Exception exp) {
                System.out.println("failed for " + pathParams + "-------------------------------------------------------------" + i);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        pathParams = "aaa";
        for (int i = 0; i < 100; i++) {
            try {
                restTemplate.getForEntity("http://0.0.0.0:8080/diff-gateway/" + pathParams, String.class);
                System.out.println("success for " + pathParams + "-------------------------------------------------------------" + i);
            } catch (Exception exp) {
                System.out.println("failed for " + pathParams + "-------------------------------------------------------------" + i);
            }
        }
    }
}
