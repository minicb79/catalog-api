package com.minicdesign.catalog.api.items.repositories.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJpaRepository extends JpaRepository<RecipeDao, Long> {
}
