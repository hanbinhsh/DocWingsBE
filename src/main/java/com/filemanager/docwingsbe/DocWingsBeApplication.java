package com.filemanager.docwingsbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class DocWingsBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocWingsBeApplication.class, args);
    }

}
