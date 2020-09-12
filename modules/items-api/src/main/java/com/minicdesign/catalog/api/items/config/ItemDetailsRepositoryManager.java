package com.minicdesign.catalog.api.items.config;

import java.util.Arrays;
import java.util.List;

import com.minicdesign.catalog.api.items.repositories.BookRepositoryAdapter;
import com.minicdesign.catalog.api.items.repositories.ItemRepositoryAdapter;
import com.minicdesign.catalog.api.items.repositories.RecipeRepositoryAdapter;
import com.minicdesign.catalog.api.items.repositories.RepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ItemDetailsRepositoryManager implements InitializingBean {

    @Autowired
    private final ItemRepositoryAdapter itemRepository;

    @Autowired
    private final BookRepositoryAdapter bookRepository;

    @Autowired
    private final RecipeRepositoryAdapter recipeRepository;

    private List<RepositoryAdapter> repositoryAdapters;

    @Override
    public void afterPropertiesSet() throws Exception {
        repositoryAdapters = Arrays.asList(itemRepository, bookRepository, recipeRepository);
    }

    @Bean
    public List<RepositoryAdapter> getRepositoryAdapters() {
        return repositoryAdapters;
    }
}
