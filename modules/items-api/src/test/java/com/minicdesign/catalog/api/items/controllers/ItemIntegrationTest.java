package com.minicdesign.catalog.api.items.controllers;

import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.DeleteItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemListUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.UpdateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemIntegrationTest {

//  private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateItemUseCase mockCreateItemUseCase;
    @MockBean
    private GetItemListUseCase mockGetItemListUseCase;
    @MockBean
    private GetItemUseCase mockGetItemUseCase;
    @MockBean
    private UpdateItemUseCase updateItemUseCase;
    @MockBean
    private DeleteItemUseCase deleteItemUseCase;

    @Test
    public void givenInvalidRequestWithMissingType_whenCreateItem_thenExceptionThrown() throws Exception {

        String bookJson = "{\"title\": \"Book Title\", \"author\": \"Book Author\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/libraries/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors", hasItem("Invalid \"type\". Must be one of \"boardgame\", \"book\", \"magazine\", \"recipe\", or \"url\".")));
    }

    @Test
    public void givenInvalidRequestWithMissingTitleOnBook_whenCreateItem_thenExceptionThrown() throws Exception {

        String bookJson = "{\"type\": \"book\", \"author\": \"Book Author\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/libraries/1/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors", hasItem("Invalid \"type\". Must be one of \"boardgame\", \"book\", \"magazine\", \"recipe\", or \"url\".")));

        verify(mockCreateItemUseCase, times(0)).createItem(any(ItemDomain.class));
    }
}

@SpringBootApplication
class TestApplication {
}
