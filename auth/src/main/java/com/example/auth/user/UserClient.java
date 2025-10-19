package com.example.auth.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user")
public interface UserClient {
  @GetMapping("/{username}")
  ResponseEntity<User> findUserWithUsername(@PathVariable String username);
}
