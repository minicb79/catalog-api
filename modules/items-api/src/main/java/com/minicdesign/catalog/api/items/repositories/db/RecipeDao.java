package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
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

    @Override
    public ItemDomain daoToDomain() {
        ItemDomain domain = new ItemDomain();
        domain.setId(getId());
        domain.setType(ItemType.RECIPE);
        domain.setTitle(getTitle());
        domain.setSubtitle(getSubtitle());
        domain.setSummary(getSummary());
        domain.setLibraryId(getLibrary().getId());
        domain.setMeal(getMeal());
        return domain;
    }
}
