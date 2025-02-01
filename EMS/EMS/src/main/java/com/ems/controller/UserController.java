package com.ems.controller;

import com.ems.dto.SignUpRequest;
import com.ems.entity.User;
import com.ems.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody SignUpRequest user){
        return ResponseEntity.ok().body(authenticationService.signUp(user));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createViewOnlyUser")
    public ResponseEntity<User> createViewOnlyUser(@RequestBody SignUpRequest user){
        return ResponseEntity.ok().body(authenticationService.createViewOnlyUser(user));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok().body(authenticationService.deleteUser(id));
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok().body(authenticationService.getAll());
    }

}
