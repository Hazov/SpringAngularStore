package ru.voronasever.voronaStore.controller;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.request.GetProductsRequest;
import ru.voronasever.voronaStore.payload.response.MessageResponse;
import ru.voronasever.voronaStore.payload.response.ProductsPageResponse;
import ru.voronasever.voronaStore.services.CategoryService;
import ru.voronasever.voronaStore.services.ProductService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    @PostMapping("/create-from-csv")
    ResponseEntity<MessageResponse> createProduct(@RequestParam MultipartFile file) {
        return productService.createProductsFromCsvFile(file);
    }

    @GetMapping("/searchProducts")
    List<Product> search(@RequestParam String term) {
        return productService.search(term).toList();
    }

    @PostMapping("/create")
    ResponseEntity<MessageResponse> createProducts(@RequestBody Product product) throws IOException {
        productService.saveToDb(product);
        productService.saveToEs(product);
        return new ResponseEntity<>(new MessageResponse("Добавлен новый продукт " + product.getName()),HttpStatus.CREATED);
    }



    @PostMapping("/remove")
    ResponseEntity<MessageResponse> deleteProduct(@RequestBody Product product){
        productService.removeFromDb(product);
        productService.removeFromEs(product);
        return new ResponseEntity<>(new MessageResponse("Добавлен новый продукт"),HttpStatus.ACCEPTED);
    }
}
