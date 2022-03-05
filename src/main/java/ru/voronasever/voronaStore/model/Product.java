package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import ru.voronasever.voronaStore.dto.ProductCsvDto;

import javax.persistence.*;
import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "products")
@Document(indexName = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Integer id;
    @Column(name = "product_article")
    String article;
    @Column(name = "product_name")
    String name;
    @Column(name = "product_description")
    String description;
    @Column(name = "product_price")
    int price;
    @ManyToOne()
    @JoinColumn(name = "product_category")
    Category category;
    @Column(name = "product_count")
    int countOnStock;
    @ManyToMany()
    @JoinTable(
            name = "products_reviews",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    Collection<Review> reviews;


    public Product(ProductCsvDto productCsv,Category category) {
        this.article = productCsv.getArticle();
        this.name = productCsv.getName();
        this.description = productCsv.getDescription();
        this.price = productCsv.getPrice();
        this.category = category;
        this.countOnStock = productCsv.getCountOnStock();
    }
}
