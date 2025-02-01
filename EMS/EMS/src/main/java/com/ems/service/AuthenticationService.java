package com.ems.service;


import com.ems.dto.JwtAuthenticationResponse;
import com.ems.dto.SignInRequest;
import com.ems.dto.SignUpRequest;
import com.ems.entity.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);


}
