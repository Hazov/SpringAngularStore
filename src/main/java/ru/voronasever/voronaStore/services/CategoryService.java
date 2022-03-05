package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.repositories.jpa.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }
    @Transactional
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public Optional<Category> getCategoryById(short id) {
        return categoryRepository.findById(id);
    }
}
