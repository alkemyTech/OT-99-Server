package com.alkemy.ong.repository;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository underTest;

    public CategoryRepositoryTest() {
    }

//    @BeforeAll
//    public static void setUpClass() {
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//    }
//
//    @BeforeEach
//    public void setUp() {
//    }
//
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of save method, of class CategoryRepository.
     */
//    @Disabled
//    @Test
//    public void testSave() {
//        System.out.println("save");
//        CategoryDto cat = null;
//        CategoryRepository instance = new CategoryRepositoryImpl();
//        Category expResult = null;
//        Category result = instance.save(cat);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of findByName method, of class CategoryRepository.
     */
//    @Disabled
//    @Test
//    
//    public void testFindByName() {
//        System.out.println("findByName");
//        String name = "";
//        CategoryRepository instance = new CategoryRepositoryImpl();
//        Optional<Category> expResult = null;
//        Optional<Category> result = instance.findByName(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of findById method, of class CategoryRepository.
     */
    @Test
    public void testFindById() {

        System.out.println("findById");

//Given
Category category = new Category();
category.setCreationDate(LocalDateTime.now());
category.setName("blabla");
category.setImage("image");
category.setDescription("description");
category.setCreationDate(LocalDateTime.now());

//        Category category = new Category(
//                1L,
//                "Festejos",
//                "description",
//                "image",
//                false,
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
        underTest.save(category);
        System.out.println(category.getId());
        //when
        Category categTest = underTest.findById(category);
        System.out.println(categTest.getId());
        //then
        assertThat(categTest).isEqualTo(category);

    }

}
