package com.daniel.gestiondestock.dto;

import lombok.Data;

@Data
public class ChangeUserPasswordDto {
  
  private Integer id; 
  private String password;
  private String confirmPassword; 

  public  ChangeUserPasswordDto(){}
}
