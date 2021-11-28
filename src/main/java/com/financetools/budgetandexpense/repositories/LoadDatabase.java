package com.financetools.budgetandexpense.repositories;

import java.util.Date;

import com.financetools.budgetandexpense.entities.Expense;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LoadDatabase
 */
// @Configuration
public class LoadDatabase {

  private static final Logger log = 
  LoggerFactory.getLogger(LoadDatabase.class);

  // @Bean
  // CommandLineRunner initDatabase(ExpenseRepository repository) {
  //   return args -> {
  //     log.info("Preloading ", repository.save(new Expense("Chicken 2kg", 334.0, new Date())));
  //     log.info("Preloading ", repository.save(new Expense("Mutton 800g", 600.0, new Date())));
  //   };
  // }
}