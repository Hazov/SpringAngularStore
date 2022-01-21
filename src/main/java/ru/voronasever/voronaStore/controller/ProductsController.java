package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.request.GetProductsRequest;
import ru.voronasever.voronaStore.services.CategoryService;
import ru.voronasever.voronaStore.services.ProductService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/products")
public class ProductsController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    List<Product> showProducts(@RequestBody GetProductsRequest productRequest){
        int currentPage = productRequest.getCurrentPage();
        int pageSize = productRequest.getProductsOnPage();
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        String currentCategory = productRequest.getCategory();
        Optional<Category> category = categoryService.getCategoryByName(currentCategory);
        if(category.isPresent()){
            return productService.findAllByCategory(category.get(), pageRequest);
        }
       return productService.findAll(pageRequest);
    }

    @PostMapping("/create")
    String createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return "Все хорошо";
    }

    @PostMapping("/remove")
    void deleteProduct(@RequestBody Product product){
        productService.removeProduct(product);
    }

//    @GetMapping("/count/{categoryStr}") //TODO cache
//    Integer getProductsCount(@PathVariable String categoryStr){
//        Optional<Category> category = categoryService.getCategoryByName(categoryStr);
//        if(category.isPresent()){
//            return productService.getPagesByCategoryCount(category.get());
//            //return productService.getCountOfProductsByCategory(category.get());
//        }
//        return productService.getAllPegesCount();
//        //return productService.getCountOfProducts();
//    }
}
