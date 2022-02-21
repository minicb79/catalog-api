package com.minicdesign.catalog.api.libraries.repositories.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryJpaRepository extends JpaRepository<LibraryDao, Long> {

    int countById(Long id);
}
