package com.minicdesign.catalog.api.libraries.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "library")
@Data
@NoArgsConstructor
public class LibraryDao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 40)
  private String name;

  @Column(length = 160)
  private String description;

  public LibraryDao(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public static LibraryDao from(LibraryDomain domain) {
    LibraryDao dao = new LibraryDao();
    domain.setId(domain.getId());
    domain.setName(domain.getName());
    domain.setDescription(domain.getDescription());
    return dao;
  }
}
