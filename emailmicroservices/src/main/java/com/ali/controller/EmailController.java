package com.ali.controller;

import com.ali.dto.request.EmailSaveUserProfile;
import com.ali.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


//    @PostMapping("/save")
//    public ResponseEntity<Boolean> saveEmail(@RequestBody EmailSaveUserProfile emailSaveUserProfile){
//        return ResponseEntity.ok(emailService.save(emailSaveUserProfile));
//    }
}
