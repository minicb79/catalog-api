package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.Data;

@Entity(name="item")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class ItemDao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  protected Long id;

  @Column(nullable = false, length = 60)
  protected String title;

  @Column(length = 120)
  protected String subtitle;

  @Column(length = 1024)
  protected String summary;

  @ManyToOne
  @JoinColumn(name="library_id")
  protected LibraryDao library;

}
