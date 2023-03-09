package com.ali.manager;

import com.ali.dto.request.EmailSaveUserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "email-feign",url = "http://localhost:8082/mail",decode404 = true
)
public interface IEmailManager {

    @PostMapping("/save")
    ResponseEntity<Boolean> saveEmail(@RequestBody EmailSaveUserProfile emailSaveUserProfile);
}
