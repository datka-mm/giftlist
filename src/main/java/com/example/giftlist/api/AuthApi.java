package com.example.giftlist.api;

import com.example.giftlist.db.services.UserService;
import com.example.giftlist.dto.request.AuthRequest;
import com.example.giftlist.dto.request.ForgotPasswordRequest;
import com.example.giftlist.dto.request.RegisterRequest;
import com.example.giftlist.dto.request.ResetPasswordRequest;
import com.example.giftlist.dto.response.AuthResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/public")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Auth Api", description = "Authentication and Authorization")
public class AuthApi {

    private final UserService userService;

    @Operation(summary = "Sign up",description = "Any user can register")
    @PostMapping("register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @Operation(summary = "Sign in",description = "Only registered users can login")
    @PostMapping("login")
    public AuthResponse login(@RequestBody @Valid AuthRequest authRequest) throws MessagingException {
        return userService.login(authRequest);
    }

    @Operation(summary = "Google authentication", description = "Authenticate with google")
    @PostMapping("auth-google")
    public AuthResponse authWithGoogle(String tokenId) throws FirebaseAuthException {
        return userService.authWithGoogle(tokenId);
    }

    @Operation(summary = "Forgot password", description = "Send link forgot password")
    @PutMapping("forgot-password")
    public SimpleResponse forgotPassword(@RequestParam String email,
                                         @RequestParam String link) throws MessagingException {
        return userService.forgotPassword(email,link);
    }

    @Operation(summary = "Reset Password", description = "Change password")
    @PutMapping("reset-password/{id}")
    public SimpleResponse resetPassword(@PathVariable("id") Long id,
                                        @RequestBody @Valid ResetPasswordRequest request){
        return userService.resetPassword(id,request);
    }

    @Operation(summary = "Change password", description = "Link to change password")
    @PutMapping("change-password")
    public SimpleResponse changePasswordOnForgot(@RequestBody @Valid ForgotPasswordRequest forgotPassword) {
        return userService.changeOnForgot(forgotPassword);
    }
}