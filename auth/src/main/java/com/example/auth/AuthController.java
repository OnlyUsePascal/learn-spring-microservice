package com.example.auth;

import org.springframework.web.bind.annotation.RestController;

import com.example.auth.user.UserClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {
  @Autowired
  UserClient userClient;

  @PostMapping("/login")
  public String login(@RequestBody LoginPayload payload) {
    var status = "";

    try {
      var u = userClient.findUserWithUsername(payload.getUsername()).getBody();
      status = "locked in! user email is: " + u.getEmail();
    } catch (Exception e) {
      status = "nah bro";
    }

    return status;
  }

}
