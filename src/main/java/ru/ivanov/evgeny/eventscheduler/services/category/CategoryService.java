package ru.ivanov.evgeny.eventscheduler.services.category;

import ru.ivanov.evgeny.eventscheduler.persistence.domain.Category;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    Category submit(CategoryDto categoryDto);

    CategoryDto fetchCategoryById(Long id);

    List<CategoryDto> fetchAllCategories();

}
