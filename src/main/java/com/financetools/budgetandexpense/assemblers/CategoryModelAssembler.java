package com.financetools.budgetandexpense.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.financetools.budgetandexpense.controllers.CategoryController;
import com.financetools.budgetandexpense.entities.Category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {

  @Override
  public EntityModel<Category> toModel(Category entity) {
    return EntityModel.of(entity,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class).get(entity.getId())).withSelfRel(),
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class).getAll()).withRel("categories"));
  }
  
  private EntityModel<Category> toModelForCollection(Category category) {
    return EntityModel.of(category,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class).get(category.getId())).withSelfRel());
  }
  
  @Override
  public CollectionModel<EntityModel<Category>> toCollectionModel(Iterable<? extends Category> entities) {
    List<EntityModel<Category>> categories = new ArrayList<>();
    entities.iterator().forEachRemaining((category) -> categories.add(toModelForCollection(category)));
    return CollectionModel.of(categories,
    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class).getAll()).withSelfRel());
  }
}
