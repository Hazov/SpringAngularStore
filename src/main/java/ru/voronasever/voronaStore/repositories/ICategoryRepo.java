package ru.voronasever.voronaStore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Category;

@Repository
public interface ICategoryRepo extends CrudRepository<Category, Short> {
    Category findCategoryByName(String category);
}
