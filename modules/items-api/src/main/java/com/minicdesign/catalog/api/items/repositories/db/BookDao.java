package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "book")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookDao extends ItemDao {

    @Column(length = 60)
    private String author;

    @Column(length = 20)
    private String isbn;

    @Column(length = 20)
    private String barcode;

    @Column
    private Integer pages;

}
