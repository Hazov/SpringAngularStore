package ru.voronasever.voronaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.request.GetProductsRequest;
import ru.voronasever.voronaStore.services.CategoryService;
import ru.voronasever.voronaStore.services.ProductService;

import java.util.List;



@RestController
@RequestMapping("api/v1/products")
public class ProductsController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    List<Product> showProducts(@RequestBody GetProductsRequest productRequest){
        String currentCategory = productRequest.getCategory();
        if(!currentCategory.equals("all")){
            Category category = categoryService.getCategoryByName(currentCategory);
            return productService.getPageOfProductsByCategory(
                    category, productRequest.getProductsOnPage(), productRequest.getCurrentPage());
        }
       return productService.getPageOfProducts(productRequest.getProductsOnPage(), productRequest.getCurrentPage());
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

    @GetMapping("/count/{categoryStr}") //TODO cache
    Long getProductsCount(@PathVariable String categoryStr){
        if(!categoryStr.equals("all")){
            Category category = categoryService.getCategoryByName(categoryStr);
            return productService.getCountOfProductsByCategory(category);
        }
        return productService.getCountOfProducts();
    }
}
