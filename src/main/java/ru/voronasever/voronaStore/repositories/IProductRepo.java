package ru.voronasever.voronaStore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;

import java.util.List;

@Repository
public interface IProductRepo extends PagingAndSortingRepository<Product, Integer> {
    long count();
    long countProductByCategory(Category category);


    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
//