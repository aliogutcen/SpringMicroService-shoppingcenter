package com.ali.service;

import com.ali.dto.request.AuthRegisterRequestDto;
import com.ali.dto.request.LoginAuthRequestDto;
import com.ali.dto.request.UpdateAuthRequestDto;
import com.ali.exception.AuthMicroServiceException;
import com.ali.exception.ErrorType;
import com.ali.mapper.IAuthMapper;
import com.ali.rabbitmq.model.CreateUser;

import com.ali.rabbitmq.producer.UserProducer;
import com.ali.repository.IAuthRepository;
import com.ali.repository.entity.Auth;
import com.ali.repository.enums.EStatus;
import com.ali.utility.JwtTokenManager;
import com.ali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final UserProducer userProducer;
    private final JwtTokenManager jwtTokenManager;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, UserProducer userProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProducer = userProducer;

    }

    public Boolean registerAuth(AuthRegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuthRegister(dto);
        save(auth);
        CreateUser createUser = IAuthMapper.INSTANCE.toCreateUser(auth);
        userProducer.createSendMessage(createUser);

        return true;
    }

    public Optional<String> loginAuth(LoginAuthRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.LOGIN_FAILED);
        return jwtTokenManager.generateJwtToken(optionalAuth.get().getId(), optionalAuth.get().getErole());
    }

    public Boolean updateAuth(UpdateAuthRequestDto dto) {

        Optional<Auth> optionalAuth = findById(dto.getAuthid());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.AUTH_NOT_FOUND);
        optionalAuth.get().setMail(dto.getMail());
        optionalAuth.get().setUsername(dto.getUsername());
        update(optionalAuth.get());
        return true;
    }

    public Boolean deleteAuth(Long id) {
        Optional<Auth> optionalAuth = findById(id);
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.AUTH_NOT_FOUND);
        optionalAuth.get().setEstatus(EStatus.DELETED);
        update(optionalAuth.get());
        return true;
    }


}
