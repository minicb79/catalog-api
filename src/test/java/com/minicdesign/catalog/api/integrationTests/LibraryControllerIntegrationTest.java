package com.minicdesign.catalog.api.integrationTests;

import static org.springframework.test.annotation.DirtiesContext.ClassMode;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minicdesign.catalog.api.libraries.controllers.domain.request.LibraryDetailsRequest;
import org.junit.jupiter.api.BeforeEach;
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
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class LibraryControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void resetDB() {

  }

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
        .andExpect(jsonPath("$.id").value(15L))
        .andExpect(jsonPath("$.name").value("Library Name"))
        .andExpect(jsonPath("$.description").value("Library Description"));
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
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.errorCode").value("VE-001"))
        .andExpect(jsonPath("$.validationErrors").exists())
        .andExpect(jsonPath("$.validationErrors").isMap())
        .andExpect(jsonPath("$.validationErrors").isNotEmpty());
  }

  @Test
  public void testNullNameWhenCreateLibrary() throws Exception {
    LibraryDetailsRequest library = new LibraryDetailsRequest();
    library.setDescription("A description for the library.");

    mockMvc.perform(post("/libraries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(library)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.errorCode").value("VE-001"))
        .andExpect(jsonPath("$.validationErrors").exists())
        .andExpect(jsonPath("$.validationErrors").isMap())
        .andExpect(jsonPath("$.validationErrors").isNotEmpty());
  }

  @Test
  public void testGetDefaultPagedLibraryList() throws Exception {
    mockMvc.perform(get("/libraries"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.libraryList").isArray())
        .andExpect(jsonPath("$.libraryList.length()").value(6))
        .andExpect(jsonPath("$.libraryList[4].name").value("Library 5"))
        .andExpect(jsonPath("$.page").value(0))
        .andExpect(jsonPath("$.count").value(14))
        .andExpect(jsonPath("$.pageCount").value(3));
  }

  @Test
  public void testGetThirdPageLibraryList() throws Exception {
    mockMvc.perform(get("/libraries?page=2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.libraryList").isArray())
        .andExpect(jsonPath("$.libraryList.length()").value(2))
        .andExpect(jsonPath("$.libraryList[1].name").value("Library 14"));
  }

  @Test
  public void testRequestInvalidPage() throws Exception {
    mockMvc.perform(get("/libraries?page=5"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.libraryList").isEmpty());
  }

  @Test
  public void testRequestLibraryDetails() throws Exception {
    mockMvc.perform(get("/libraries/4"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(4L))
        .andExpect(jsonPath("$.name").value("Library 4"))
        .andExpect(jsonPath("$.description").value("Library Description 4"));
  }

  @Test
  public void testRequestInvalidLibraryDetails() throws Exception {
    mockMvc.perform(get("/libraries/199"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value("NF-404"));
  }

  @Test
  public void testUpdateLibraryDetails() throws Exception {

    LibraryDetailsRequest library = new LibraryDetailsRequest();
    library.setName("Library 5.1");
    library.setDescription("Library Description 5.1");

    mockMvc.perform(put("/libraries/5")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(library)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Library 5.1"))
        .andExpect(jsonPath("$.description").value("Library Description 5.1"));
  }
}
