package com.financetools.budgetandexpense.controllers;

import com.financetools.budgetandexpense.assemblers.BudgetModelAssembler;
import com.financetools.budgetandexpense.entities.Budget;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.BudgetRepository;

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
public class BudgetController {
  
  private final BudgetRepository repository;
  private final BudgetModelAssembler assembler;

  BudgetController(BudgetRepository repository, BudgetModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/budgets")
  public CollectionModel<EntityModel<Budget>> getAll() {
    return assembler.toCollectionModel(repository.findAll());
  }

  @GetMapping("/budgets/{id}")
  public EntityModel<Budget> get(@PathVariable Long id) {
    Budget budget = repository.findById(id)
    .orElseThrow(() -> new BENotFoundException(Budget.getEntityName(), id));

    return assembler.toModel(budget);
  }

  @PostMapping("/budgets")
  public ResponseEntity<?> add(@RequestBody Budget budget) {
    EntityModel<Budget> entityModel = assembler.toModel(repository.save(budget));
    return ResponseEntity
    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
    .body(entityModel);
  }
}
