package com.azarenka;

import com.azarenka.javafx.ApplicationStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Start application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class Application extends ApplicationStarter {
    public static void main(String[] args) {
        startApplication(args, Application.class);
    }
}
