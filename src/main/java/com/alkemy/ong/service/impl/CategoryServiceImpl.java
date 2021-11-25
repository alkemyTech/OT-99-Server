package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryPageDto;
import com.alkemy.ong.exception.DataAlreadyExistException;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryPageDto getAllCategories(int pageNumber) throws NotFoundException {
        Pageable page = PageRequest.of(pageNumber, 10);
        Page<Category> resultPage = categoryRepository.findAll(page);
        if (pageNumber + 1 > resultPage.getTotalPages()) {
            throw new NotFoundException("The page of number " + pageNumber + " doesn't exist");
        }
        return createCategoryPageDto(resultPage);
    }

    private CategoryPageDto createCategoryPageDto(Page<Category> page) {
        CategoryPageDto dto = new CategoryPageDto();
        dto.setList(categoryMapper.toCategoryDtoGetAllList(page.getContent()));
        if (page.hasNext()) {
            dto.setNextPage("/categories?page=" + page.nextPageable().getPageNumber());
        }
        if (page.hasPrevious()) {
            dto.setPreviusPage("/categories?page=" + page.previousPageable().getPageNumber());
        }
        return dto;
    }

    @Override
    public Category save(CategoryDto categoryDto) throws DataAlreadyExistException {

        if ((categoryRepository.findByName(categoryDto.getName()).isPresent())) {
            throw new DataAlreadyExistException("Wrong! Name already exists.");
        }
        Category category = categoryMapper.dtoToEntity(categoryDto);
        category.setCreationDate(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, CategoryDto categoryDto) throws NotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The category is not registered."));
        category.setUpdatedAt(LocalDateTime.now());
        category.setImage(categoryDto.getImage());
        category.setName(categoryDto.getName());
        categoryDto.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(long id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wrong! Category doesn't exist."));
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Sorry! Category doesn't exist.");
        }
    }

}
