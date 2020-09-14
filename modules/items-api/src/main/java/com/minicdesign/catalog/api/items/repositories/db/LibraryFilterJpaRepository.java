package com.minicdesign.catalog.api.items.repositories.db;

import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LibraryFilterJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Page<T> findAllByLibrary(LibraryDao library, Pageable pageable);
}
