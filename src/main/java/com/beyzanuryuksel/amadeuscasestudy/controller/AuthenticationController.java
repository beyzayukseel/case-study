package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.model.CreateUserDto;
import com.beyzanuryuksel.amadeuscasestudy.model.LoginRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.UserResponse;
import com.beyzanuryuksel.amadeuscasestudy.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserDetailsImpl userDetails;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetails.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserDto createUserDto) {
        userDetails.register(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
}
