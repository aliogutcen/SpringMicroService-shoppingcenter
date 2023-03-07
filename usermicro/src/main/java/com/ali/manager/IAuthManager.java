package com.ali.manager;

import com.ali.dto.request.UpdateAuthRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ali.constant.EndPoints.UPDATE;

@FeignClient(
        name = "auth-feign-client",
        url = "http://localhost:8080/v1/api/auth",
        decode404 = true
)
public interface IAuthManager {

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAuth(@RequestBody UpdateAuthRequestDto dto);
}
