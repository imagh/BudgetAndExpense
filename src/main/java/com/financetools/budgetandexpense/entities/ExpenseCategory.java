package com.financetools.budgetandexpense.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ExpenseCategory {
  
  private @Id @GeneratedValue Long id;
  @ManyToOne
  private Category category;
  private Double expenseLimit;
  @JsonBackReference
  @ManyToOne
  @JoinColumn
  private Budget budget;
  @ElementCollection
  @JsonManagedReference
  @OneToMany(targetEntity = Expense.class)
  @JoinColumn
  private List<Expense> expenses;

  ExpenseCategory() {}

  public ExpenseCategory(Category category, Double expenseLimit, Budget budget, List<Expense> expenses) {
    this.category = category;
    this.expenseLimit = expenseLimit;
    this.budget = budget;
    if (expenses == null) {
      this.expenses = new ArrayList<>();
    } else {
      this.expenses = expenses;
    }
  }

  public Long getId() {
    return id;
  }

  public Double getExpenseLimit() {
    return expenseLimit;
  }

  public Budget getBudget() {
    return budget;
  }

  public Category getCategory() {
    return category;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setBudget(Budget budget) {
    this.budget = budget;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setLimit(Double expenseLimit) {
    this.expenseLimit = expenseLimit;
  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public void addExpense(Expense expense) {
    expenses.add(expense);
    expense.setExpenseCategory(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ExpenseCategory) {
      if (obj == this) {
        return true;
      }
      ExpenseCategory other = (ExpenseCategory) obj;
      if (this.id.equals(other.getId())) {
        return true;
      } else if (this.category.equals(other.getCategory()) && this.budget.equals(other.getBudget())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getBudget(), getCategory(), getExpenseLimit(), getExpenses());
  }

  @Override
  public String toString() {
    return String.format("CategoryBudget{id=%d, category=%s, budget=%s, categoryBudget=%f, expenses=%s}",
     getId(), getCategory().toString(), getBudget().toString(), getExpenseLimit(), getExpenses().toString());
  }

  public static String getEntityName() {
    return "CategoryBudget";
  }
}
