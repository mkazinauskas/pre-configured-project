package preconfigured.authorization;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableAuthorizationServer
@SpringBootApplication
public class AuthorizationApplication {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
