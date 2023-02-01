package kr.co.damdauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DamdaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamdaUserApplication.class, args);
    }

}
