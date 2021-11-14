package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.repositories.ICategoryRepo;

import java.util.List;

@Service
public class CategoryService{
    private ICategoryRepo categoryRepo;
    @Autowired
    public void setCategoryRepo(ICategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @Transactional
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepo.findAll();
    }
    @Transactional
    public Category getCategoryByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }
}
