package com.minicdesign.catalog.api.libraries.controllers.usecases;

import org.springframework.stereotype.Service;

@Service
public interface GetLibraryCountUseCase {
  long getCount();

  // TODO: Implement SearchParams/Filter.
  //       This will filter the results, thus reducing the count.
  // long getCount(SearchParams p);
}
