package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "recipe")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecipeDao extends ItemDao {

  @Column
  private String meal;
}
