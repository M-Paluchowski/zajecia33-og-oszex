package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.dto.CategoryDto;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
            .map(category -> toDto(category))
            .collect(Collectors.toList());
    }

    private CategoryDto toDto(final Category category) {
        return new CategoryDto(category.getName(), category.getDescription());
    }

    public List<String> findCategoriesNames() {
        return categoryRepository.findAll().stream()
            .map(Category::getName)
            .collect(Collectors.toList());
    }
}
