package ru.voronasever.voronaStore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.voronasever.voronaStore.dto.ProductCsvDto;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.payload.response.MessageResponse;
import ru.voronasever.voronaStore.repositories.elasticsearch.IElasticProductRepo;
import ru.voronasever.voronaStore.repositories.jpa.ProductRepository;
import utils.files.CsvFileParser;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    IElasticProductRepo elasticProductRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;


    @Transactional
    public void saveToDb(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void removeFromDb(Product product) {
        productRepository.delete(product);
    }

    @Transactional
    public void removeFromEs(Product product) {
        elasticProductRepository.delete(product);
    }


    @Transactional
    public Page<Product> findAllByCategory(Category category, Pageable pageable){
        return productRepository.findAllByCategory(category, pageable);

    }
    @Transactional
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Transactional
    public void saveToEs(Product product) {
        elasticProductRepository.save(product);
    }



    public ResponseEntity<MessageResponse> createProductsFromCsvFile(MultipartFile file) {
        List<Product> products = null;
        try {
            products = getProductsFromCsv(file);
            //productRepository.saveAll(products);

            productRepository.saveAllOnConflictDoNothing(products);
            //elasticProductRepository.saveAll(products);
        } catch (IOException e) {
           return new ResponseEntity<>(new MessageResponse("Проблема с файлом"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new MessageResponse("Файлы были добавлены"), HttpStatus.CREATED);
    }

    private List<Product> getProductsFromCsv(MultipartFile file) throws IOException {
        List<ProductCsvDto> productsCsv = loadProductsFromCsv(file);
        Map<Short,Category> categoriesMap = new HashMap<>();
        List<Product> products = new ArrayList<>();
        short currentCategoryCsv;
        for (ProductCsvDto prodCsv:productsCsv) {
            currentCategoryCsv = prodCsv.getCategory();
            if(categoriesMap.containsKey(currentCategoryCsv)){
                Product product = new Product(prodCsv, categoriesMap.get(prodCsv.getCategory()));
                products.add(product);
            }else{
                Optional<Category> currentCategory = categoryService.getCategoryById(currentCategoryCsv);
                if(currentCategory.isPresent()){
                    categoriesMap.put(currentCategoryCsv, currentCategory.get());
                    Product product = new Product(prodCsv, currentCategory.get());
                    products.add(product);
                }
            }
        }
        return products;
    }

    public  List<ProductCsvDto> loadProductsFromCsv(MultipartFile multipartFile) throws IOException {
        return CsvFileParser.loadObjectListFromCsv(ProductCsvDto.class, multipartFile);
    }

    public Page<Product> search(String term) {
        return elasticProductRepository.findByName(term, Pageable.unpaged());
    }
}

