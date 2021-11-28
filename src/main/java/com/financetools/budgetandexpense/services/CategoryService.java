package com.financetools.budgetandexpense.services;

import java.util.List;

import com.financetools.budgetandexpense.entities.Category;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  
  private static CategoryService instance = null;

  @Autowired
  private CategoryRepository repository;

  private CategoryService() {
  }

  public List<Category> getAll() {
    return repository.findAll();
  }

  public Category getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new BENotFoundException(Category.getEntityName(), id));
  }

  public Category save(Category category) {
    return repository.save(category);
  }

  public static CategoryService getInstance() {
    if (instance == null) {
      instance = new CategoryService();
    }
    return instance;
  }
}
