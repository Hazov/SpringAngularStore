package ru.voronasever.voronaStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "products")
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
    int count;
    @Column(name = "product_reviews")
    @OneToMany()
    Collection<Feedback> feedbacks;

    @JsonIgnore()
    @ManyToMany(mappedBy = "products")
    Set<Cart> set = new HashSet<>();
}
