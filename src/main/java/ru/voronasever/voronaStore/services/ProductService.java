package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.repositories.IProductRepo;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private IProductRepo productRepo;

    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void removeProduct(Product product) {
        productRepo.delete(product);
    }

    @Transactional
    public Page<Product> findAllByCategory(Category category, Pageable pageable){
        return productRepo.findAllByCategory(category, pageable);
    }
    @Transactional
    public Page<Product> findAll(Pageable pageable){
        return productRepo.findAll(pageable);
    }
}

