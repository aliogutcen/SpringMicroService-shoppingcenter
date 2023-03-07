package com.ali.utility;


import com.ali.exception.ErrorType;
import com.ali.exception.UserMicroServiceException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {
    private final String password = "${PASSWORD}";

    public Optional<String> generateJwtToken(Long id) {
        String token;
        long extraTime = 1000L * 60 * 15;
        token = JWT.create().withAudience()
                .withClaim("id", id)
                .withIssuer("admin")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + extraTime))
                .sign(Algorithm.HMAC512(password));
        if (token.isEmpty()) throw new UserMicroServiceException(ErrorType.TOKEN_ERROR);
        return Optional.of(token);
    }

    public Boolean isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(password);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("admin").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT != null;
        } catch (Exception exception) {
            throw new UserMicroServiceException(ErrorType.TOKEN_VALID_ERROR);
        }
    }

    public Optional<Long> decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(password);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("admin").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) throw new UserMicroServiceException(ErrorType.TOKEN_VALID_ERROR);
            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (Exception exception) {
            throw new UserMicroServiceException(ErrorType.TOKEN_VALID_ERROR);
        }
    }
}
