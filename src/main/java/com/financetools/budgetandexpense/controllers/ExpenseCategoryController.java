package com.financetools.budgetandexpense.controllers;

import com.financetools.budgetandexpense.assemblers.ExpenseCategoryModelAssembler;
import com.financetools.budgetandexpense.entities.ExpenseCategory;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.ExpenseCategoryRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseCategoryController {
  
  private final ExpenseCategoryRepository repository;
  private final ExpenseCategoryModelAssembler assembler;

  ExpenseCategoryController(ExpenseCategoryRepository repository, ExpenseCategoryModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/expense_categories")
  public
  CollectionModel<EntityModel<ExpenseCategory>> getAll() {
    return assembler.toCollectionModel(repository.findAll());
  }

  @GetMapping("/expense_categories/{id}")
  public
  EntityModel<ExpenseCategory> get(@PathVariable Long id) {
    ExpenseCategory category = repository.findById(id)
    .orElseThrow(() -> new BENotFoundException(ExpenseCategory.getEntityName(), id));

    return assembler.toModel(category);
  }

  @PostMapping("/expense_categories")
  public
  ResponseEntity<?> add(@RequestBody ExpenseCategory categoryBudget) {
    EntityModel<ExpenseCategory> entityModel = assembler.toModel(repository.save(categoryBudget));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
    .toUri()).body(entityModel);
  }
}
