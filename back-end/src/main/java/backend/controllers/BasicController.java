package backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

}
