package cn.work;

import cn.work.spring.config.LibraryApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
