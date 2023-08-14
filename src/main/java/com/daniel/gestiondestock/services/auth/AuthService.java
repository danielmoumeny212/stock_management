package com.daniel.gestiondestock.services.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.auth.AuthenticationRequest;
import com.daniel.gestiondestock.dto.auth.AuthenticationResponse;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository repository;


  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = this.repository.findUserByEmail(request.getEmail())
        .orElseThrow(
            () -> new EntityNotFoundException("Password or Email is wrong ", ErrorCodes.UTILISATEUR_NOT_FOUND));
    var jwtToken = this.jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

}
