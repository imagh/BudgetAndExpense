package com.financetools.budgetandexpense.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
public class Budget {
	
	@Id @GeneratedValue
	private Long id;
	private @Column(unique = true) String name;
	@ReadOnlyProperty
	private Double amount;
	private Date startDate;
	private Date endDate;
	@JsonManagedReference
	@OneToMany(targetEntity = ExpenseCategory.class)
	@JoinColumn
	private List<ExpenseCategory> expenseCategories;

	Budget() {}

	public Budget(String name, Date startDate, Date endDate, List<ExpenseCategory> expenseCategories) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		if (expenseCategories == null) {
			this.expenseCategories = new ArrayList<>();
		} else {
			this.expenseCategories = expenseCategories;
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<ExpenseCategory> getExpenseCategories() {
		return expenseCategories;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Double getAmount() {
		amount = 0.0;
		if (expenseCategories != null) {
			for (ExpenseCategory expenseCategory : expenseCategories) {
				amount += expenseCategory.getExpenseLimit();
			}
		}
		return amount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpenseCategories(List<ExpenseCategory> expenseCategories) {
		this.expenseCategories = expenseCategories;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addExpenseCategories(ExpenseCategory expenseCategory) {
		expenseCategories.add(expenseCategory);
		expenseCategory.setBudget(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Budget) {
			if (this == obj) {
				return true;
			}
			Budget other = (Budget) obj;
			if (this.id.equals(other.getId()) || this.name.equals(other.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}

	@Override
	public String toString() {
		return String.format("Budget{id=%d, name=%s, startDate=%T, endDate=%T, expenseCategories=%s", 
		getId(), getName(), getStartDate(), getEndDate(), getExpenseCategories().toString());
	}

	public static String getEntityName() {
		return "Budget";
	}
}
