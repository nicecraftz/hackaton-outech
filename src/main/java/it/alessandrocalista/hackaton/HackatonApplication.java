package it.alessandrocalista.hackaton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class HackatonApplication {

    public static void main(String[] args) {
        if (System.getenv("OPENAI_API_KEY") == null) {
            log.error("OPENAI_API_KEY environment variable NOT FOUND");
            System.exit(1);
            return;
        }

        SpringApplication.run(HackatonApplication.class, args);
    }
}
