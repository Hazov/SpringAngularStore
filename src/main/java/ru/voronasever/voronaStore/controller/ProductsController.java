package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.request.GetProductsRequest;
import ru.voronasever.voronaStore.payload.response.ProductsPageResponse;
import ru.voronasever.voronaStore.services.CategoryService;
import ru.voronasever.voronaStore.services.ProductService;

import java.util.ArrayList;
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
    ResponseEntity<ProductsPageResponse> showProducts(@RequestBody GetProductsRequest productRequest){
        PageRequest pageRequest = PageRequest.of(productRequest.getCurrentPage(), productRequest.getProductsOnPage());
        String currentCategory = productRequest.getCategory();
        Page<Product> productsPage;
        if(currentCategory.equals("all")){
            productsPage = productService.findAll(pageRequest);
        }else{
            Optional<Category> category = categoryService.getCategoryByName(currentCategory);
            if(category.isPresent())
                productsPage = productService.findAllByCategory(category.get(), pageRequest);
            else
                productsPage = productService.findAll(pageRequest);
        }
        ProductsPageResponse productsPageResponse =
                new ProductsPageResponse(productsPage.getContent(), productsPage.getTotalPages());
        return new ResponseEntity<>(productsPageResponse, HttpStatus.OK);
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
}
