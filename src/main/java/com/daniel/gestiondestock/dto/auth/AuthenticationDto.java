package com.daniel.gestiondestock.dto.auth;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class AuthenticationDto {

  String email;
  String password; 
  
}
