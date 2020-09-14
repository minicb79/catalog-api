package com.minicdesign.catalog.api.items.repositories.db;

import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends LibraryFilterJpaRepository<BookDao, Long> {
}
