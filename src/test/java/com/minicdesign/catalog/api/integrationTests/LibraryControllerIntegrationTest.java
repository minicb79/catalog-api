package com.minicdesign.catalog.api.integrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.integrationTests.repositories.db.LibraryJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LibraryControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private LibraryJpaRepository jpaRepository;

  @Test
  public void testCreateLibrary() throws Exception {
    LibraryDetailsRequest library = new LibraryDetailsRequest();
    library.setName("Library Name");
    library.setDescription("Library Description");

    mockMvc.perform(post("/libraries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(library)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Library Name"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Library Description"));
  }

  @Test
  public void testValidationWhenCreateLibrary() throws Exception {
    LibraryDetailsRequest library = new LibraryDetailsRequest();
    library.setName("This is a Library with a very very very very very very very long name");

    mockMvc.perform(post("/libraries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(library)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

}
