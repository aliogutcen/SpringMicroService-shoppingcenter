package com.ali.controller;

import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ali.constant.EndPoints.REGISTER;
import static com.ali.constant.EndPoints.USER;

@RestController
@RequestMapping(USER) //localhost:8091/v1/api/user
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(REGISTER)
    public ResponseEntity<?> registerUserProfile(@RequestBody UserProfileRegisterDto dto) {
        return ResponseEntity.ok(userProfileService.registerUserProfile(dto));
    }
}
