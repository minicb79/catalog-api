package com.minicdesign.catalog.api.integrationTests.repositories.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryJpaRepository extends JpaRepository<LibraryDao, Long> {
}
