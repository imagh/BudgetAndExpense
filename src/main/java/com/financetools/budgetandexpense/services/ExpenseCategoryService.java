package com.financetools.budgetandexpense.services;

import com.financetools.budgetandexpense.entities.ExpenseCategory;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.ExpenseCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseCategoryService {

  private static ExpenseCategoryService instance = null;

  @Autowired
  private ExpenseCategoryRepository repository;

  private ExpenseCategoryService() {}

  public ExpenseCategory getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new BENotFoundException(ExpenseCategory.getEntityName(), id));
  }

  public static ExpenseCategoryService getInstance() {
    if (instance == null) {
      instance = new ExpenseCategoryService();
    }
    return instance;
  }
  
}
