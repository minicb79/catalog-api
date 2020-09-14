package com.minicdesign.catalog.api.items.repositories.db;

import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJpaRepository extends JpaRepository<ItemDao, Long> {

    Page<ItemDao> findAllByLibrary(LibraryDao library, Pageable pageable);
}
