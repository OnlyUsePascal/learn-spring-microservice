package com.example.auth.user;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user")
public interface UserClient {
  @GetMapping("/{username}")
  ResponseEntity<User> findUserWithUsername(@PathVariable String username);

  @PostMapping("/")
  ResponseEntity<UUID> createUser(@RequestBody CreateUserDto dto);
}
