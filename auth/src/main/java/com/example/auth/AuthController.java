package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_client.CreateUserDto;
import com.example.user_client.UserFeignClient;

@RestController
public class AuthController {
  @Autowired
  UserFeignClient userClient;

  @PostMapping("/sign-in")
  public ResponseEntity<String> signIn(@RequestBody SignInDto dto) {
    var status = "";

    try {
      var u = userClient.findUserWithUsername(dto.getUsername()).getBody();
      status = "locked in! user email is: " + u.email();
    } catch (Exception e) {
      status = "nah bro";
    }

    return ResponseEntity.ok(status);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<String> signUp(@RequestBody SignUpDto dto) {
    try {
      var uid = userClient.createUser(new CreateUserDto(
          dto.getUsername(),
          dto.getPassword(),
          dto.getEmail())).getBody();
      return ResponseEntity.ok(uid.toString());
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
