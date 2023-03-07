package com.ali.service;

import com.ali.dto.request.AuthActivationRequestDto;
import com.ali.dto.request.AuthRegisterRequestDto;
import com.ali.dto.request.LoginAuthRequestDto;
import com.ali.dto.request.UpdateAuthRequestDto;
import com.ali.dto.response.ActivateCodeGeneratorResponseDto;
import com.ali.exception.AuthMicroServiceException;
import com.ali.exception.ErrorType;
import com.ali.manager.IUserProfileManager;
import com.ali.mapper.IAuthMapper;
import com.ali.repository.IAuthRepository;
import com.ali.repository.entity.Auth;
import com.ali.repository.enums.EStatus;
import com.ali.utility.ActivateCodeGenerator;
import com.ali.utility.JwtTokenManager;
import com.ali.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final IUserProfileManager userProfileManager;

    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, IUserProfileManager userProfileManager, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userProfileManager = userProfileManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    public ActivateCodeGeneratorResponseDto registerAuth(AuthRegisterRequestDto dto) {
        if (dto.getPassword().equals(dto.getRepassword())) {
            Auth auth = IAuthMapper.INSTANCE.toAuthRegister(dto);
            auth.setActivateCode(ActivateCodeGenerator.codeGenerator());
            save(auth);
            userProfileManager.registerUserProfile(IAuthMapper.INSTANCE.toUserProfileRegister(auth));
            return IAuthMapper.INSTANCE.activateCodeGeneratorResponseDto(auth);
        } else throw new AuthMicroServiceException(ErrorType.AUTH_PASSWORD_ERROR);
    }

    public void activationAuth(AuthActivationRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByIdAndActivateCode(dto.getId(), dto.getActivateCode());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.TOKEN_VALID_ERROR);
        optionalAuth.get().setEstatus(EStatus.ACTIVE);
        update(optionalAuth.get());
    }

    public Optional<String> loginAuth(LoginAuthRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.LOGIN_FAILED);
        return jwtTokenManager.generateJwtToken(optionalAuth.get().getId());
    }

    public Boolean updateAuth(UpdateAuthRequestDto dto) {

        Optional<Auth> optionalAuth = findById(dto.getAuthid());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.AUTH_NOT_FOUND);
        optionalAuth.get().setMail(dto.getMail());
        optionalAuth.get().setUsername(dto.getUsername());
        update(optionalAuth.get());
        return true;
    }
}
