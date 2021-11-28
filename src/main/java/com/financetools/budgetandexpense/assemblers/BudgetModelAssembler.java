package com.financetools.budgetandexpense.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.financetools.budgetandexpense.controllers.BudgetController;
import com.financetools.budgetandexpense.entities.Budget;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BudgetModelAssembler implements RepresentationModelAssembler<Budget, EntityModel<Budget>> {

  @Override
  public EntityModel<Budget> toModel(Budget budget) {
    return EntityModel.of(budget,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BudgetController.class).get(budget.getId())).withSelfRel(),
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BudgetController.class).getAll()).withRel("budgets")
    );
  }

  private EntityModel<Budget> toModelForCollection(Budget budget) {
    return EntityModel.of(budget,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BudgetController.class).get(budget.getId())).withSelfRel());
  }
  
  @Override
  public CollectionModel<EntityModel<Budget>> toCollectionModel(Iterable<? extends Budget> entities) {
    List<EntityModel<Budget>> budgets = new ArrayList<>();
    entities.iterator().forEachRemaining((budget) -> budgets.add(toModelForCollection(budget)));
    return CollectionModel.of(budgets,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BudgetController.class).getAll()).withSelfRel());
  }
}
