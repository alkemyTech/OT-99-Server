package com.alkemy.ong.category;

import com.alkemy.ong.controller.CategoryController;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDtoGetAll;
import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.exception.DataAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ValidationException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    CategoryService categoryService;
//
    @Autowired
    CategoryController categoryController;

    List<CategoryDtoGetAll> categoryGetAll;
    PageDto<CategoryDtoGetAll> pageDto;

    CategoryDtoGetAll categoryDtoGet;
    CategoryDtoGetAll categoryDtoGet2;

    CategoryDto categoryDto;
    CategoryDto categoryDto2;

    Category category;
    Category category2;

    public CategoryControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws NotFoundException, DataAlreadyExistException {
        mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        category = new Category();
        category.setId(1L);
        category.setName("Salud");
        category.setDescription("blabla");
        category.setImage("image");

        categoryDto = new CategoryDto();
        categoryDto.setName("Novedades");
        categoryDto.setDescription("descriptiongoeshere");

        categoryDto2 = new CategoryDto();
        categoryDto2.setName("NameUpdated");
        categoryDto2.setDescription("DescriptionUpdated");

        category2 = new Category();
        category2.setId(2L);
        category2.setName(categoryDto.getName());
        category2.setDescription(categoryDto.getDescription());

        categoryDtoGet = new CategoryDtoGetAll("salud");
        categoryDtoGet2 = new CategoryDtoGetAll("entretenimiento");

        categoryGetAll = new ArrayList<>();
        pageDto = new PageDto<>();

        categoryGetAll.add(categoryDtoGet);
        categoryGetAll.add(categoryDtoGet2);
        pageDto.setList(categoryGetAll);

        Mockito.when(categoryService.getAllCategories(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pageDto);
        Mockito.when(categoryService.save(categoryDto)).thenReturn(category2);
        Mockito.when(categoryService.update(1L, categoryDto2)).thenReturn(category);

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
        PageDto<CategoryDtoGetAll> dto = categoryService.getAllCategories(0, 2);
        assertThat(dto.getList()).isNotEmpty();
        assertThat(dto.getList().size()).isEqualTo(2);
        
        String request = objectMapper.writeValueAsString(dto);
        String page = "?page=0";
        String amount = "&size=2";
        mockMvc.perform(get("/categories/"+page+amount)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testGetAllCategoriesWithoutAuthorization() throws Exception {
        PageDto<CategoryDtoGetAll> dto = categoryService.getAllCategories(0, 2);
        assertThat(dto.getList()).isNotEmpty();
        assertThat(dto.getList().size()).isEqualTo(2);

        mockMvc
                .perform(get("/categories/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

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
    public void testGetByIdWithoutAuthorization() throws Exception {
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

        String request = objectMapper.writeValueAsString(category2);

        Category categoryCreate = categoryService.save(categoryDto);
        assertThat(categoryCreate.getId()).isEqualTo(2L);
        assertThat(categoryCreate.getName()).isEqualTo("Novedades");
        mockMvc.perform(post("/categories/create/")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testCreateWithoutAuthorization() throws Exception {

        Category categoryCreate = categoryService.save(categoryDto);
        assertThat(categoryCreate.getId()).isEqualTo(2L);
        assertThat(categoryCreate.getName()).isEqualTo("Novedades");

        mockMvc
                .perform(post("/categories/create")
                        .contentType(MediaType.APPLICATION_JSON))
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
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException() instanceof ValidationException));
    }

    /**
     * Test of updateCategory method, of class CategoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateCategory() throws Exception {
        Long id = 1L;
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Salud");
        category.setName(categoryDto2.getName());
        category.setDescription(categoryDto2.getDescription());
        Category categoryUpdateAfter = categoryService.update(1L, categoryDto2);
        assertThat(categoryUpdateAfter.getId()).isEqualTo(1L);
        assertThat(categoryUpdateAfter.getName()).isEqualTo("NameUpdated");
        assertThat(categoryUpdateAfter.getDescription()).isEqualTo("DescriptionUpdated");

        String request = objectMapper.writeValueAsString(categoryDto2);
        mockMvc.perform(put("/categories/" + id + "/")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})

    public void testUpdateCategoryWithoutName() throws Exception {
        String param = "{\"name\":\"\",\"description\":\"DescriptionUpdated\"}";
        Long id = 1L;
        mockMvc
                .perform(put("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException() instanceof ValidationException));

    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void testUpdateCategoryWithoutAuthorization() throws Exception {
        Long id = 1L;
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Salud");
        category.setName(categoryDto2.getName());
        category.setDescription(categoryDto2.getDescription());
        Category categoryUpdateAfter = categoryService.update(1L, categoryDto2);
        assertThat(categoryUpdateAfter.getId()).isEqualTo(1L);
        assertThat(categoryUpdateAfter.getName()).isEqualTo("NameUpdated");
        assertThat(categoryUpdateAfter.getDescription()).isEqualTo("DescriptionUpdated");
        mockMvc
                .perform(put("/categories/" + id + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}
