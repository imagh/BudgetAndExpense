package com.financetools.budgetandexpense.repositories;

import com.financetools.budgetandexpense.entities.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
  
}
