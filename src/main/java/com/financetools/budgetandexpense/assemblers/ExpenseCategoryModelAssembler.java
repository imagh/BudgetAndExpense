package com.financetools.budgetandexpense.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.financetools.budgetandexpense.controllers.ExpenseCategoryController;
import com.financetools.budgetandexpense.entities.ExpenseCategory;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCategoryModelAssembler implements RepresentationModelAssembler<ExpenseCategory, EntityModel<ExpenseCategory>> {

  @Override
  public EntityModel<ExpenseCategory> toModel(ExpenseCategory entity) {
    return EntityModel.of(entity,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseCategoryController.class).get(entity.getId())).withSelfRel(),
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseCategoryController.class).getAll()).withRel("categories_budgets"));
  }
  
  private EntityModel<ExpenseCategory> toModelForCollection(ExpenseCategory categoryBudget) {
    return EntityModel.of(categoryBudget,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseCategoryController.class).get(categoryBudget.getId())).withSelfRel());
  }
  
  @Override
  public CollectionModel<EntityModel<ExpenseCategory>> toCollectionModel(Iterable<? extends ExpenseCategory> entities) {
    List<EntityModel<ExpenseCategory>> categoryBudgets = new ArrayList<>();
    entities.iterator().forEachRemaining((categoryBudget) -> categoryBudgets.add(toModelForCollection(categoryBudget)));
    return CollectionModel.of(categoryBudgets,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExpenseCategoryController.class).getAll()).withSelfRel());
  }
  
}
