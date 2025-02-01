package com.ems.service.impl;

import com.ems.dto.JwtAuthenticationResponse;
import com.ems.dto.SignInRequest;
import com.ems.dto.SignUpRequest;
import com.ems.entity.Role;
import com.ems.entity.User;
import com.ems.exception.ApplicationException;
import com.ems.repository.UserRepository;
import com.ems.service.AuthenticationService;
import com.ems.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User signUp(SignUpRequest signUpRequest){
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        if (!signUpRequest.getPassword().matches(passwordPattern)) {
            throw new RuntimeException("Password must have at least one uppercase letter, one lowercase letter, one special character, and one digit.");
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
        var user =userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Email not found"));
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new ApplicationException("","Invalid email or password","",HttpStatus.BAD_REQUEST);
        }
        var jwt =jwtService.generateToken(user);
        var refreshToken =jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public List<User> getAll() {
        return userRepository.findAll().stream().filter(e-> e.getRole().equals(Role.USER)).collect(Collectors.toList());
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
    public User createViewOnlyUser(SignUpRequest signUpRequest){
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        if (!signUpRequest.getPassword().matches(passwordPattern)) {
            throw new RuntimeException("Password must have at least one uppercase letter, one lowercase letter, one special character, and one digit.");
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.VIEW_ONLY);
        return userRepository.save(user);
    }

}
