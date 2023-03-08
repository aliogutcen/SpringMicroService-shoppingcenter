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
import com.ali.rabbitmq.model.CreateUser;
import com.ali.rabbitmq.producer.CreateUserProducer;
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
    private final CreateUserProducer createUserProducer;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, IUserProfileManager userProfileManager, JwtTokenManager jwtTokenManager, CreateUserProducer createUserProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userProfileManager = userProfileManager;
        this.jwtTokenManager = jwtTokenManager;
        this.createUserProducer = createUserProducer;
    }

    public ActivateCodeGeneratorResponseDto registerAuth(AuthRegisterRequestDto dto) {
        if (dto.getPassword().equals(dto.getRepassword())) {
            Auth auth = IAuthMapper.INSTANCE.toAuthRegister(dto);
            auth.setActivateCode(ActivateCodeGenerator.codeGenerator());
            save(auth);
            CreateUser createUser = IAuthMapper.INSTANCE.toCreateUser(auth);
            createUserProducer.createSendMessage(createUser);
            // userProfileManager.registerUserProfile(IAuthMapper.INSTANCE.toUserProfileRegister(auth));
            return IAuthMapper.INSTANCE.activateCodeGeneratorResponseDto(auth);
        } else throw new AuthMicroServiceException(ErrorType.AUTH_PASSWORD_ERROR);
    }

    public void activationAuth(AuthActivationRequestDto dto) {
        Optional<Auth> optionalAuth = authRepository.findOptionalByIdAndActivateCode(dto.getId(), dto.getActivateCode());
        if (optionalAuth.isEmpty()) throw new AuthMicroServiceException(ErrorType.TOKEN_VALID_ERROR);
        optionalAuth.get().setEstatus(EStatus.ACTIVE);
        update(optionalAuth.get());
        userProfileManager.activationUserProfileStatus(IAuthMapper.INSTANCE.toUserProfileActivateStatus(optionalAuth.get()));
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
