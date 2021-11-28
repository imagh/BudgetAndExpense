package com.financetools.budgetandexpense.controllers;

import com.financetools.budgetandexpense.assemblers.ExpenseModelAssembler;
import com.financetools.budgetandexpense.entities.Expense;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.ExpenseRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
  
  private final ExpenseRepository repository;
  private final ExpenseModelAssembler assembler;

  ExpenseController(ExpenseRepository repository, ExpenseModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/expenses")
  public
  CollectionModel<EntityModel<Expense>> getAll() {
    return assembler.toCollectionModel(repository.findAll());
    // List<EntityModel<Expense>> expenses = repository.findAll().stream()
    // .map(assembler::toModel)
    // .collect(Collectors.toList());

    // return CollectionModel.of(expenses, 
    // WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/expenses")
  public ResponseEntity<?> add(@RequestBody Expense expense) {
    EntityModel<Expense> entityModel = assembler.toModel(repository.save(expense));
    return ResponseEntity.
    created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
    .body(entityModel);
  }

  // Single item

  @GetMapping("/expenses/{id}")
  public
  EntityModel<Expense> get(@PathVariable Long id) {
    Expense expense = repository.findById(id)
    .orElseThrow(() -> new BENotFoundException(Expense.getEntityName(), id));

    return assembler.toModel(expense);
  }

  @PutMapping("/expenses/{id}")
  public ResponseEntity<?> edit(@RequestBody Expense newExpense, @PathVariable Long id) {
    
    Expense updatedExpense = repository.findById(id)
      .map(expense -> {
        expense.setDescription(newExpense.getDescription());
        expense.setAmount(newExpense.getAmount());
        expense.setDate(newExpense.getDate());
        return repository.save(expense);
      })
      .orElseThrow(() -> new BENotFoundException(Expense.getEntityName(), id));

    EntityModel<Expense> entityModel = assembler.toModel(updatedExpense);
    return ResponseEntity
    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
    .body(entityModel);
  }

  @DeleteMapping("/expenses/{id}")
  ResponseEntity<?> deleteExpense(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
