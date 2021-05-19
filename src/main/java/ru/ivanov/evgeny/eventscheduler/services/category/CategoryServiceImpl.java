package ru.ivanov.evgeny.eventscheduler.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CategoryRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Category;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.CategoryDto;
import ru.ivanov.evgeny.eventscheduler.services.mappers.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Category submit(CategoryDto categoryDto) {
        Category category;
        if (categoryDto.getId() != null) {
            category = categoryRepository.findById(categoryDto.getId()).orElse(null);
            if (category == null) {
                throw new IllegalStateException("category.id");
            }
        } else {
            category = new Category();
            category.setName(categoryDto.getName());
            category = categoryRepository.save(category);
        }
        return category;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto fetchCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new IllegalStateException("Category not found by id");
        }
        return categoryMapper.mapToDto(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> fetchAllCategories() {
        return categoryRepository.findAll().stream().map(c -> categoryMapper.mapToDto(c)).collect(Collectors.toList());
    }

}
