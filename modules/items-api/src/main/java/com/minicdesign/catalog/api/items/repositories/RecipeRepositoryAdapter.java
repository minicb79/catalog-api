package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.repositories.db.RecipeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryAdapter {

  private final RecipeJpaRepository jpaRepository;

}
