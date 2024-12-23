package com.concert.interfaces.api.auth;

import com.concert.application.auth.AuthFacade;
import com.concert.application.auth.dto.LoginCommand;
import com.concert.interfaces.api.auth.dto.LoginRequest;
import com.concert.interfaces.api.auth.dto.LoginResponse;
import com.concert.interfaces.api.common.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request,
                                                            HttpServletResponse response) {
        LoginCommand command = LoginCommand.builder().name(request.name()).build();
        String accessToken = authFacade.login(command);
        boolean isSuccessLogin = accessToken != null;
        response.setHeader(HttpHeaders.AUTHORIZATION, accessToken);
        return ResponseEntity
                .ok(ApiResponse.success(LoginResponse.builder().isSuccess(isSuccessLogin)
                        .build()));
    }
}
