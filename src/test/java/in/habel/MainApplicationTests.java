package in.habel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void givenMemUsers_whenGetPingWithValidUser_thenOk() {
        ResponseEntity<String> result
                = makeRestCallToGetPing("memuser", "pass");

        Assert.assertEquals(result.getStatusCodeValue(), 200);
        Assert.assertEquals(result.getBody(), "OK");
    }

    private ResponseEntity<String>
    makeRestCallToGetPing(String username, String password) {
        return restTemplate.withBasicAuth(username, password)
                .getForEntity("/api/ping", String.class, Collections.emptyMap());
    }

    private ResponseEntity<String> makeRestCallToGetPing() {
        return restTemplate
                .getForEntity("/api/ping", String.class, Collections.emptyMap());
    }
}
