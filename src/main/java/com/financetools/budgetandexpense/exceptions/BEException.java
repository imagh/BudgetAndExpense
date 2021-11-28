package com.financetools.budgetandexpense.exceptions;

public class BEException extends RuntimeException {

  private Integer errorCode;
  
  public BEException(Integer errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public static final Integer ID_NOT_FOUND = 0;
}
