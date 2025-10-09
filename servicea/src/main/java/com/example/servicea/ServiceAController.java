package com.example.servicea;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {
  static List<User> users = Arrays.asList(
      new User("email 1", "name 1 "),
      new User("email 2", "name 2 "));

  @GetMapping("/greeting")
  public String getGreeting() {
    return "hello from service a";
  }

  @GetMapping("/user")
  public List<User> listUsers() {
    return users;
  }

  @GetMapping("/user/{id}")
  public User listUsers(@PathVariable int id) {
    return users.get(id);
  }
}
