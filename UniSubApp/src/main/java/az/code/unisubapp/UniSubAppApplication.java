package az.code.unisubapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UniSubAppApplication implements CommandLineRunner {
    ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(UniSubAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
