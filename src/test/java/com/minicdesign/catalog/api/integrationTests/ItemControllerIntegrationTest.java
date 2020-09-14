package com.minicdesign.catalog.api.integrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import com.minicdesign.catalog.api.items.domain.ItemType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateItem() throws Exception {
        ItemDetailsRequest book = new ItemDetailsRequest();
        book.setTitle("New Title");
        book.setSubtitle("New Subtitle");
        book.setAuthor("New Author");
        book.setIsbn("New ISBN");
        book.setBarcode("New Barcode");
        book.setType(ItemType.BOOK);

        mockMvc.perform(post("/libraries/2/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(8L))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.subtitle").value("New Subtitle"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.isbn").value("New ISBN"))
                .andExpect(jsonPath("$.libraryId").value(2L))
                .andExpect(jsonPath("$.type").value("book"));

        ItemDetailsRequest recipe = new ItemDetailsRequest();
        recipe.setTitle("New Recipe");
        recipe.setSubtitle("New Recipe Subtitle");
        recipe.setType(ItemType.RECIPE);
        recipe.setMeal("breakfast");

        mockMvc.perform(post("/libraries/3/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(9L))
                .andExpect(jsonPath("$.title").value("New Recipe"))
                .andExpect(jsonPath("$.meal").value("breakfast"))
                .andExpect(jsonPath("$.isbn").doesNotExist());
    }

    @Test
    public void testCreateItemWithExtraPropertiesInRequest() throws Exception {
        ItemDetailsRequest book = new ItemDetailsRequest();
        book.setTitle("New Title");
        book.setSubtitle("New Subtitle");
        book.setAuthor("New Author");
        book.setIsbn("New ISBN");
        book.setBarcode("New Barcode");
        book.setType(ItemType.BOOK);
        book.setMeal("lunch");

        mockMvc.perform(post("/libraries/3/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(8L))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.libraryId").value(3L))
                .andExpect(jsonPath("$.type").value("book"))
                .andExpect(jsonPath("$.meal").doesNotExist());
    }

    @Test
    public void testValidationOnCreate() throws Exception {
        // Missing mandatory title and type, isbn and barcode too long, pages negative
        // Meal is irrelevant and not an exception
        ItemDetailsRequest book = new ItemDetailsRequest();
        book.setSubtitle("New Subtitle");
        book.setAuthor("New Author");
        book.setIsbn("New ISBN that is too long");
        book.setBarcode("New Barcode that is waaay tooooo looooooong");
        book.setPages(-5);
        book.setMeal("lunch");

        mockMvc.perform(post("/libraries/3/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("VE-001"))
                .andExpect(jsonPath("$.validationErrors").exists())
                .andExpect(jsonPath("$.validationErrors").isMap())
                .andExpect(jsonPath("$.validationErrors").isNotEmpty())
                .andExpect(jsonPath("$.validationErrors.length()").value(5));
    }

    @Test
    public void testInvalidLibrary() throws Exception {
        ItemDetailsRequest recipe = new ItemDetailsRequest();
        recipe.setTitle("New Recipe");
        recipe.setSubtitle("New Recipe Subtitle");
        recipe.setType(ItemType.RECIPE);
        recipe.setMeal("breakfast");

        // Library with ID of 24 does not exist in the test data.
        mockMvc.perform(post("/libraries/24/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("NF-404"));
    }

    @Test
    public void testGetPageOfItems() throws Exception {
        mockMvc.perform(get("/libraries/3/items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.itemList.length()").value(3));
    }

    @Test
    public void testGetItem() throws Exception {
        mockMvc.perform(get("/items/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.title").value("Item 4"))
                .andExpect(jsonPath("$.type").value("book"));
    }

    @Test
    public void testGetItemWithInvalidId() throws Exception {
        mockMvc.perform(get("/items/399"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("NF-404"));
    }

    @Test
    public void testDeleteItem() throws Exception {
        mockMvc.perform(get("/items/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L));

        mockMvc.perform(delete("/items/2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/items/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteItemWithInvalidId() throws Exception {
        mockMvc.perform(get("/items/200"))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/items/200"))
                .andExpect(status().isOk());
    }
}
