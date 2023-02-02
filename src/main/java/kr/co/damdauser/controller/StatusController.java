package kr.co.damdauser.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class StatusController {

    private final Environment environment;

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/welcome")
    public String welcome(){
        return message;
    }

    @GetMapping("/port_check")
    public String check(HttpServletRequest request){
        log.info("Server Port={}", request.getServerPort());
        return String.format("Hi, there. This is a message from User Service on Port %s."
                , environment.getProperty("local.server.port"));
    }

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service.";
    }
}
