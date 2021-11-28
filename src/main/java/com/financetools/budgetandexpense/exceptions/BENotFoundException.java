package com.financetools.budgetandexpense.exceptions;

public class BENotFoundException extends BEException {
  
  public BENotFoundException(String entityName, Long id) {
    super(BEException.ID_NOT_FOUND, String.format("Could not found %s %d", entityName, id));
  }
}
