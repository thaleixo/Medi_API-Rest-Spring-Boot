package medi.app.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloControler {

    @GetMapping
    public String sayHello() {
        return "Hello World Spring!";
    }

    @PutMapping
    public String sayHelloPut() {
        return "Hello World Spring!";
    }
}
