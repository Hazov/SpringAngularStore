package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends ProductRepositoryCustom, PagingAndSortingRepository<Product, Integer> {
    long count();
    long countProductByCategory(Category category);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
@Repository
interface ProductRepositoryCustom {
    @Transactional
    void saveAllOnConflictDoNothing(List<Product> productList);
}


