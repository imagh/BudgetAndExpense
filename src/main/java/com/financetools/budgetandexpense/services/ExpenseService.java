package com.financetools.budgetandexpense.services;

import java.util.List;

import com.financetools.budgetandexpense.entities.Expense;
import com.financetools.budgetandexpense.entities.ExpenseCategory;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {

  private static ExpenseService instance = null;

  @Autowired
  private ExpenseRepository repository;

  private ExpenseService() {
  }

  public List<Expense> getAll() {
    return repository.findAll();
  }

  public Expense getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new BENotFoundException(Expense.getEntityName(), id));
  }

  public Expense save(Expense expense) {
    Long expenseCategoryId = expense.getExpenseCategory().getId();
    ExpenseCategory expenseCategory = ExpenseCategoryService.getInstance().getById(expenseCategoryId);
    expense.setExpenseCategory(expenseCategory);
    return repository.save(expense);
  }

  public static ExpenseService getInstance() {
    if (instance == null) {
      instance = new ExpenseService();
    }
    return instance;
  }
}