package com.financetools.budgetandexpense.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
  
	private @Id @GeneratedValue Long id;
	private @Column(unique = true) String name;

	Category () {}

	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Category) {
			if (this == obj) {
				return true;
			}
			Category o = (Category) obj;
			if (this.id.equals(o.getId())) {
				return true;
			} else if (this.name.equals(o.getName())) {
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
		return String.format("Category{id=%d, name=%s}", 
		getId(), getName());
	}

	public static String getEntityName() {
		return "Category";
	}
}
