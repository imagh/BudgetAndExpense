package com.financetools.budgetandexpense.repositories;

import com.financetools.budgetandexpense.entities.ExpenseCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
  
}
