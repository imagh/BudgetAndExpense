package com.financetools.budgetandexpense.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.financetools.budgetandexpense.controllers.ExpenseController;
import com.financetools.budgetandexpense.entities.Expense;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExpenseModelAssembler implements RepresentationModelAssembler<Expense, EntityModel<Expense>> {
  
  @Override
  public EntityModel<Expense> toModel(Expense expense) {
    return EntityModel.of(expense,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseController.class).get(expense.getId())).withSelfRel(),
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseController.class).getAll()).withRel("expenses"));
  }

  private EntityModel<Expense> toModelForCollection(Expense expense) {
    return EntityModel.of(expense,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseController.class).get(expense.getId())).withSelfRel());
  }

  @Override
  public CollectionModel<EntityModel<Expense>> toCollectionModel(Iterable<? extends Expense> entities) {
    List<EntityModel<Expense>> expenses = new ArrayList<>();
    entities.iterator().forEachRemaining((expense) -> expenses.add(toModelForCollection(expense)));
    return CollectionModel.of(expenses,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseController.class).getAll()).withSelfRel());
    //  .stream()
    // .map(assembler::toModel)
    // .collect(Collectors.toList());
  }
}
