package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
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

    public ItemDomain daoToDomain() {
        ItemDomain domain = new ItemDomain();
        domain.setId(getId());
        domain.setType(ItemType.BOOK);
        domain.setTitle(getTitle());
        domain.setSubtitle(getSubtitle());
        domain.setSummary(getSummary());
        domain.setLibraryId(getLibrary().getId());
        domain.setAuthor(getAuthor());
        domain.setIsbn(getIsbn());
        domain.setBarcode(getBarcode());
        domain.setPages(getPages());
        return domain;
    }

}
