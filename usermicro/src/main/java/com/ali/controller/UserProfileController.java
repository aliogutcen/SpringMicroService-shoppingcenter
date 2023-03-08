package com.ali.controller;

import com.ali.dto.request.BaseRequestDto;
import com.ali.dto.request.UserProfileActivateStatus;
import com.ali.dto.request.UserProfileRegisterDto;
import com.ali.dto.request.UserProfileUpdateDto;
import com.ali.dto.response.UpdateUserProfileResponseDto;
import com.ali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ali.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(ACTIVATION)
    public ResponseEntity<?> activationUserProfileStatus(@RequestBody UserProfileActivateStatus userProfileActivateStatus) {
        return ResponseEntity.ok(userProfileService.activationUserProfileStatus(userProfileActivateStatus));
    }


    @PutMapping(UPDATE)
    public ResponseEntity<UpdateUserProfileResponseDto> updateUserProfile(@RequestBody UserProfileUpdateDto userProfileUpdateDto) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(userProfileUpdateDto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteUserProfile(@RequestBody BaseRequestDto baseRequestDto) {
        return ResponseEntity.ok(userProfileService.deleteUserProfile(baseRequestDto));
    }


}
