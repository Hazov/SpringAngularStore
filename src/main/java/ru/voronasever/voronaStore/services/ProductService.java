package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.repositories.IProductRepo;

import java.util.List;

@Service
public class ProductService {
    private IProductRepo productRepo;
    @Autowired
    public void setProductRepo(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Transactional
    public List<Product> getAllProducts() {
        return (List<Product>) productRepo.findAll();
    }
    @Transactional
    public List<Product> getAllProductsByCategory(Category category){
        return productRepo.findAllByCategory(category);
    }
    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void removeProduct(Product product) {
        productRepo.delete(product);
    }

    public List<Product> getPageOfProductsByCategory(Category category, int productsOnPage, int currentPage) {
        int offset = (currentPage-1)*productsOnPage;
        return productRepo.findFirstByCategoryOffset(category, offset, productsOnPage);
    }

    public List<Product> getPageOfProducts(int productsOnPage, int currentPage) {
        int offset = (currentPage-1)*productsOnPage;
        return productRepo.findFirstOffset(offset, productsOnPage);
    }

    public Long getCountOfProductsByCategory(Category category) {
        return productRepo.countProductByCategory(category);
    }
    public Long getCountOfProducts() {
        return productRepo.count();
    }
}

