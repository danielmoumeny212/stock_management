package com.daniel.gestiondestock.handlers;

import java.util.ArrayList;
import java.util.List;
import com.daniel.gestiondestock.exception.ErrorCodes;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

  private Integer httpCode;

  private  ErrorCodes code;

  private String message; 

  private List<String> errors = new ArrayList<>();

}
