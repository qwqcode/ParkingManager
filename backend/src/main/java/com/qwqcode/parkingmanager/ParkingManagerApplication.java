package com.qwqcode.parkingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ParkingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingManagerApplication.class, args);
    }

}
