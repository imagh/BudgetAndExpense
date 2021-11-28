package com.financetools.budgetandexpense.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Expense {
  
  private @Id @GeneratedValue Long id;
  private String description;
  private Double amount;
  private Date date;
  @JsonBackReference
  @ManyToOne
  private ExpenseCategory expenseCategory;

  Expense() {}

  public Expense(String description, Double amount, Date date, ExpenseCategory expenseCategory) {
    this.description = description;
    this.amount = amount;
    this.date = date;
    this.expenseCategory = expenseCategory;
  }
  
  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Double getAmount() {
    return amount;
  }

  public Date getDate() {
    return date;
  }

  public ExpenseCategory getExpenseCategory() {
    return expenseCategory;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setExpenseCategory(ExpenseCategory expenseCategory) {
    this.expenseCategory = expenseCategory;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Expense) {
      if (this == obj) {
        return true;
      }
      Expense o = (Expense) obj;
      if (this.id.equals(o.getId())) {
        return true;
      } else if (this.description.equals(o.getDescription())) {
        if (this.amount.equals(o.getAmount())) {
          if (this.date.equals(o.getDate())) {
            if (this.expenseCategory.equals(o.getExpenseCategory())) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDescription(), getAmount(), getDate(), getExpenseCategory());
  }

  @Override
  public String toString() {
    return String.format("Expense{id=%d, description=%s, amount=%f, date=%T, category=%s}",
     getId(), getDescription(), getAmount(), getDate(), getExpenseCategory().toString());
  }

  public static String getEntityName() {
    return "Expense";
  }
}
