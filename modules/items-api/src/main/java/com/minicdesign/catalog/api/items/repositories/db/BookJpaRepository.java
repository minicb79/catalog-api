package com.minicdesign.catalog.api.items.repositories.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookDao, Long> {
}
