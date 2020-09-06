package com.minicdesign.catalog.api.libraries.domain;

import com.minicdesign.catalog.api.libraries.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDomain {

  private long id;
  private String name;
  private String description;

  public static LibraryDomain from(LibraryDetailsRequest request) {
    LibraryDomain domain = new LibraryDomain();
    domain.setName(request.getName());
    domain.setDescription(request.getDescription());
    return domain;
  }

  public static LibraryDomain from(LibraryDao dao) {
    LibraryDomain domain = new LibraryDomain();
    domain.setId(dao.getId());
    domain.setName(dao.getName());
    domain.setDescription(dao.getDescription());
    return domain;
  }

}
