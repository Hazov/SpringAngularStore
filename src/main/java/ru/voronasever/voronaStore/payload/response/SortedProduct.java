package ru.voronasever.voronaStore.payload.response;

import lombok.Getter;
import lombok.Setter;
import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Feedback;
import ru.voronasever.voronaStore.model.Product;

import java.util.Collection;

@Getter
@Setter
public class SortedProduct {
    private long id;
    private String article;
    private String name;
    private String description;
    private int price;
    private Category category;
    private int countInStock;
    private final Collection<Feedback> feedbacks;
    private int countSelected;
    private int amount;


    public SortedProduct(Product product, int countSelected) {
        this.id = product.getId();
        this.article = product.getArticle();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.countInStock = product.getCount();
        this.feedbacks = product.getFeedbacks();
        this.countSelected = countSelected;
        this.amount = product.getPrice() * countSelected;
    }
}
