package mk.ukim.finki.emt.emt_lab_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmtLabBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmtLabBackendApplication.class, args);
    }

}
