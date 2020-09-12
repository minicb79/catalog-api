package com.minicdesign.catalog.api.integrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        ItemDetailsRequest item = new ItemDetailsRequest();
        item.setTitle("New Title");
        item.setSubtitle("New Subtitle");
        item.setAuthor("New Author");
        item.setIsbn("New ISBN");
        item.setBarcode("New Barcode");
        item.setType(ItemType.BOOK);

        mockMvc.perform(post("/libraries/2/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(8L))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.subtitle").value("New Subtitle"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.isbn").value("New ISBN"));
    }
}
