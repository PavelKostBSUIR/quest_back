package com.softarex.api.service;

import com.softarex.api.entity.domain.User;
import com.softarex.api.entity.dto.JwtAuthentication;
import com.softarex.api.entity.dto.JwtRequest;
import com.softarex.api.entity.dto.JwtResponse;
import com.softarex.api.repo.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//todo what is AuthException and why I use BadCredentialsException?
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;

    @Autowired
    AuthService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        User user = userRepository.findByLogin(authRequest.getLogin());
        if (user == null) {
            throw new BadCredentialsException("Unknown user login " + authRequest.getLogin());
        }
        if (user.getPassword().equals(authRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken, user.getId());
        } else {
            throw new BadCredentialsException("Incorrect password");
        }
    }

    /*public JWTResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                User user = userService.getByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JWTResponse(accessToken, null);
            }
        }
        return new JWTResponse(null, null);
    }*/

    public JwtResponse refresh(@NonNull String refreshToken) {
        boolean validated = jwtProvider.validateRefreshToken(refreshToken);
        if (validated) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken, user.getId());
            }
        }
        throw new BadCredentialsException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

