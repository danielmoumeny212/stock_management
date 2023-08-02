package com.daniel.gestiondestock.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

  private ErrorCodes errorCode; 
  

  public EntityNotFoundException(String message){
     super(message);
  }

  public EntityNotFoundException(String message, Throwable cause){
     super(message, cause);
  }

  public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCodes){
    super(message, cause);
    errorCode = errorCodes;
  }
  public EntityNotFoundException(String message, ErrorCodes errorCodes){
    super(message);
    errorCode = errorCodes;
  }
  
}
