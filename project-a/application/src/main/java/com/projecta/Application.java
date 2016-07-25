package com.projecta;

import com.projecta.domain.user.User;
import com.projecta.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner init() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                userRepository.save(new User("test@email.com"));
            }
        };
    }
}
