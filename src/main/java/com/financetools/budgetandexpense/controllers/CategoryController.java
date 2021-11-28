package com.financetools.budgetandexpense.controllers;

import com.financetools.budgetandexpense.assemblers.CategoryModelAssembler;
import com.financetools.budgetandexpense.entities.Category;
import com.financetools.budgetandexpense.exceptions.BENotFoundException;
import com.financetools.budgetandexpense.repositories.CategoryRepository;

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
public class CategoryController {
  
  private final CategoryRepository repository;
  private final CategoryModelAssembler assembler;

  CategoryController(CategoryRepository repository, CategoryModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/categories")
  public
  CollectionModel<EntityModel<Category>> getAll() {
    return assembler.toCollectionModel(repository.findAll());
  }

  @GetMapping("/categories/{id}")
  public
  EntityModel<Category> get(@PathVariable Long id) {
    Category category = repository.findById(id)
    .orElseThrow(() -> new BENotFoundException(Category.getEntityName(), id));

    return assembler.toModel(category);
  }

  @PostMapping("/categories")
  public
  ResponseEntity<?> add(@RequestBody Category category) {
    EntityModel<Category> entityModel = assembler.toModel(repository.save(category));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
    .toUri()).body(entityModel);
  }
}
