package ru.voronasever.voronaStore.payload.response;

import ru.voronasever.voronaStore.model.Category;
import ru.voronasever.voronaStore.model.Feedback;

import java.util.Collection;

public interface SortedProduct {

    long getId();

    String getArticle();

    String getName();

    Category getCategory();

    String getDescription();

    int getPrice();

    int getCountInStock();

    Collection<Feedback> getFeedbacks();

    int getCountSelected();

    int getAmount();


}
