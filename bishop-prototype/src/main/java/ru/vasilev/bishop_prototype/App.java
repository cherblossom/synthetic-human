package ru.vasilev.bishop_prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = {"ru.vasilev.starter", "ru.vasilev.bishop_prototype"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
