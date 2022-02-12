package ru.voronasever.voronaStore.payload.response;

import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Review;

import java.util.Collection;

public interface SortedProduct {

    long getId();

    String getArticle();

    String getName();

    Category getCategory();

    String getDescription();

    int getPrice();

    int getCountInStock();

    Collection<Review> getReviews();

    int getCountSelected();

    int getAmount();


}
