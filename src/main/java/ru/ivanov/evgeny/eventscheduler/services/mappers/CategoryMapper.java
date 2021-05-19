package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Category;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.CategoryDto;

@Component
public class CategoryMapper {

    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}
