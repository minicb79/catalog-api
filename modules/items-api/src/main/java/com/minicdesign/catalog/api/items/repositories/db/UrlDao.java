package com.minicdesign.catalog.api.items.repositories.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "url")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UrlDao extends ItemDao {

    @Column
    private String url;

    @Override
    public ItemDomain daoToDomain() {
        ItemDomain domain = new ItemDomain();
        domain.setId(getId());
        domain.setType(ItemType.URL);
        domain.setTitle(getTitle());
        domain.setSubtitle(getSubtitle());
        domain.setSummary(getSummary());
        domain.setLibraryId(getLibrary().getId());
        domain.setUrl(getUrl());
        return domain;
    }
}
