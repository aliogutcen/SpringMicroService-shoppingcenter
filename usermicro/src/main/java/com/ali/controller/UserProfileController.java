package com.ali.controller;

import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.dto.request.UserProfileUpdateDto;
import com.ali.dto.response.UpdateUserProfileResponseDto;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ali.constant.EndPoints.*;

@RestController
@RequestMapping(USER) //localhost:8091/v1/api/user
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(REGISTER)
    public ResponseEntity<?> registerUserProfile(@RequestBody UserProfileRegisterDto userProfileRegisterDto) {
        return ResponseEntity.ok(userProfileService.registerUserProfile(userProfileRegisterDto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<UpdateUserProfileResponseDto> updateUserProfile(@RequestBody UserProfileUpdateDto userProfileUpdateDto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(userProfileUpdateDto));
    }
}
