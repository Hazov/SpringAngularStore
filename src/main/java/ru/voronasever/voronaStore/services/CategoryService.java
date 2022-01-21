package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.repositories.ICategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    private ICategoryRepo categoryRepo;
    @Transactional
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepo.findAll();
    }
    @Transactional
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }
}
