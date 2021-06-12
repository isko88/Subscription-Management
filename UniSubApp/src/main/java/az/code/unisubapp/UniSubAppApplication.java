package az.code.unisubapp;

import az.code.unisubapp.utils.MyTimerTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniSubAppApplication implements CommandLineRunner {

    MyTimerTask myTimerTask;

    public UniSubAppApplication(MyTimerTask myTimerTask) {
        this.myTimerTask = myTimerTask;
    }
    public static void main(String[] args) {
        SpringApplication.run(UniSubAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myTimerTask.timerForCheckData();
    }

}
