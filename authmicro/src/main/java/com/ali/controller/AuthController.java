package com.ali.controller;

import com.ali.dto.request.AuthActivationRequestDto;
import com.ali.dto.request.AuthRegisterRequestDto;
import com.ali.dto.request.LoginAuthRequestDto;
import com.ali.dto.response.ActivateCodeGeneratorResponseDto;
import com.ali.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ali.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<ActivateCodeGeneratorResponseDto> registerAuth(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseEntity.ok(authService.registerAuth(dto));
    }

    @PostMapping(ACTIVATION)
    public ResponseEntity<?> activationAuth(@RequestBody AuthActivationRequestDto dto) {
        authService.activationAuth(dto);
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Optional<String>> loginAuth(@RequestBody LoginAuthRequestDto dto) {
        return ResponseEntity.ok(authService.loginAuth(dto));
    }


}
