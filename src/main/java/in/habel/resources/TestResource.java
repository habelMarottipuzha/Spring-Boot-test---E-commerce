package in.habel.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.awt.print.Pageable;

@PermitAll
@RestController
public class TestResource {

    @GetMapping(path = "/ping")
    public String ping(Pageable pageable) {
        return "pong\n";
    }

}
