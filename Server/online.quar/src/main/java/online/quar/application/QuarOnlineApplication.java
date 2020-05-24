package online.quar.application;

import online.quar.application.manager.ApplicationManager;
import online.quar.application.util.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class QuarOnlineApplication {

    public static void main(String[] args) throws Throwable {
        Logger log = Singleton.getLogger();
        ApplicationManager applicationManager = Singleton.getApplicationManager();
        applicationManager.initialiseDatabase();

        SpringApplication.run(QuarOnlineApplication.class, args);

        log.d("\n\nQuarOnline Application Loaded");
    }

}