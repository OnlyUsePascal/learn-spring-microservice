package com.example.servicea.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicea.client.GreetingDto;

@RestController
public class ServiceAController {
  @GetMapping("/")
  public String getGreeting() {
    return "hello from service a";
  }

  @PostMapping("/")
  public String sendGreeting(@RequestBody GreetingDto dto) {
    return dto.getName() + " just said: " + dto.getMessage();
  }
}
