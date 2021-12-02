package com.alkemy.ong.category;

import com.alkemy.ong.controller.CategoryController;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@RunWith(SpringRunner.class)

public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    CategoryService categoryService;

    @Autowired
    CategoryController categoryController;

    List<CategoryDtoGetAll> categoryGetAll;

    CategoryDtoGetAll categoryDtoGet;

    CategoryDto categoryDto;

    Category category;

    public CategoryControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        //SETUP MVC WITH SECURITY
        mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        
        // SETUP CATEGORY DTO GET AND GET ALL FOR /GET/CATEGORIES
        categoryDtoGet = new CategoryDtoGetAll("salud");
        categoryGetAll = new ArrayList<>();
        categoryGetAll.add(categoryDtoGet);
        
        // SETUP CATEGORY MODEL FOR /GET/CATEGORIES/{ID}
        category = new Category();
        category.setId(1L);
        category.setName("Salud");
        category.setDescription("blabla");
        category.setImage("image");

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllCategories method, of class CategoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn((List<CategoryDtoGetAll>) categoryGetAll);
        mockMvc
                .perform(get("/categories/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertThat(!categoryGetAll.isEmpty());

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testGetAllCategoriesWithoutAuthentication() throws Exception {
        when(categoryService.getAllCategories()).thenReturn((List<CategoryDtoGetAll>) categoryGetAll);
        mockMvc
                .perform(get("/categories/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        assertThat(!categoryGetAll.isEmpty());

    }

    /**
     * Test of getById method, of class CategoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetById() throws Exception {
        Long id = 1L;
        when(categoryService.getCategoryById(1)).thenReturn(category);
        mockMvc
                .perform(get("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Salud"))
                .andExpect(jsonPath("image").value("image"));
    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testGetByIdWithoutAuthentication() throws Exception {
        Long id = 1L;
        when(categoryService.getCategoryById(1)).thenReturn(category);
        mockMvc
                .perform(get("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    /**
     * Test of create method, of class CategoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreate() throws Exception {
        String param = "{\"name\":\"Entretenimiento\"}";

        mockMvc
                .perform(post("/categories/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testCreateWithoutAuthentication() throws Exception {
        String param = "{\"name\":\"Entretenimiento\"}";

        mockMvc
                .perform(post("/categories/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreateWithNullName() throws Exception {
        String param = "{\"name\":\"}";

        mockMvc
                .perform(post("/categories/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test of updateCategory method, of class CategoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateCategory() throws Exception {
        String param = "{\"name\":\"Entretenimiento\",\"description\":\"descriptionUpdated\"}";
        Long id = 1L;
        mockMvc
                .perform(put("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})

    public void testUpdateCategoryWithoutName() throws Exception {
        String param = "{\"name\":\"\",\"description\":\"descriptionUpdated\"}";
        Long id = 1L;
        mockMvc
                .perform(put("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testUpdateCategoryWithoutAuthentication() throws Exception {
        String param = "{\"name\":\"Entretenimiento\",\"description\":\"descriptionUpdated\"}";
        Long id = 1L;
        mockMvc
                .perform(put("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}
