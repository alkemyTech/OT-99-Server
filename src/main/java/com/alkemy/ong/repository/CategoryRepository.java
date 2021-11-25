package com.alkemy.ong.repository;

import java.util.Optional;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(CategoryDto cat);

    Optional<Category> findByName(String name);

    Category findById(Category category);

    Page<Category> findAll(Pageable pageRequest);

}
