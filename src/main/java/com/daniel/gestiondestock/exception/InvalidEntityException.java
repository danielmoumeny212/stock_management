package com.daniel.gestiondestock.exception;

import java.util.List;
import java.util.Map;

import lombok.Getter;

public class InvalidEntityException extends RuntimeException{
  
  @Getter
  private ErrorCodes errorCode; 

  @Getter
  private List<String> errors;

  @Getter
  private Map<String, List<String>> fieldsErros; 


  
  public InvalidEntityException(String message){
    super(message);
 }

 public InvalidEntityException(String message, Throwable cause){
    super(message, cause);
 }

 public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCodes){
   super(message, cause);
   this.errorCode = errorCodes;
 }
 public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors){
    super(message);
    this.errorCode = errorCode;
    this.errors = errors;
 }
}
