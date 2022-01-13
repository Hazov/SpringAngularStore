package ru.voronasever.voronaStore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;

import java.util.Collection;
import java.util.List;

@Repository
public interface IProductRepo extends PagingAndSortingRepository<Product, Integer> {
    List<Product> findAllByCategory(Category category);

    @Query(value="SELECT * FROM products p WHERE p.product_category = ?1 ORDER BY p.product_id offset ?2 limit ?3", nativeQuery = true)
    List<Product> findFirstByCategoryOffset(Category category, int offset, int limit);

    @Query(value="SELECT * FROM products p ORDER BY p.product_id offset ?1 limit ?2", nativeQuery = true)
    List<Product> findFirstOffset(int offset, int productsOnPage);

    long count();
    long countProductByCategory(Category category);
}
//