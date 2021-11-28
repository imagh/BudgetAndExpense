package com.financetools.budgetandexpense.advices;

import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {
  
  @ResponseBody
  @ExceptionHandler(BENotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String entityNotFoundHandler(BENotFoundException ex) {
    return ex.getMessage();
  }
}
