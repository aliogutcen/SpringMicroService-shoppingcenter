package com.ali.manager;

import com.ali.dto.request.UserProfileActivateStatus;
import com.ali.dto.request.UserProfileRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ali.constant.EndPoints.ACTIVATION;
import static com.ali.constant.EndPoints.REGISTER;

@FeignClient(

        name = "user-profile-manager",url = "http://localhost:8081/v1/api/user",decode404 = true
)
public interface IUserProfileManager {

    @PostMapping(REGISTER)
    public ResponseEntity<?> registerUserProfile(@RequestBody UserProfileRegisterDto dto);

    @PostMapping(ACTIVATION)
    public ResponseEntity<?> activationUserProfileStatus(@RequestBody UserProfileActivateStatus userProfileActivateStatus);
}
