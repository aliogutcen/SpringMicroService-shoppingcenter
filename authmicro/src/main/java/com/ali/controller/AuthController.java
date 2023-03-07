package com.ali.controller;

import com.ali.dto.request.AuthActivationRequestDto;
import com.ali.dto.request.AuthRegisterRequestDto;
import com.ali.dto.request.LoginAuthRequestDto;
import com.ali.dto.request.UpdateAuthRequestDto;
import com.ali.dto.response.ActivateCodeGeneratorResponseDto;
import com.ali.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(LOGIN)
    public ResponseEntity<Optional<String>> loginAuth(@RequestBody LoginAuthRequestDto dto) {
        return ResponseEntity.ok(authService.loginAuth(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAuth(@RequestBody UpdateAuthRequestDto dto) {
        return ResponseEntity.ok(authService.updateAuth(dto));
    }


    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<Boolean> deleteAuth(@PathVariable Long id) {
        return ResponseEntity.ok(authService.deleteAuth(id));
    }


}
