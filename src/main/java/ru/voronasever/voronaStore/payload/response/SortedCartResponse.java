package ru.voronasever.voronaStore.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
import java.util.*;

@Getter
@Setter
public class SortedCartResponse {
    List<SortedProduct> sortedProducts = new ArrayList<>();
    int totalPrice;

    public SortedCartResponse(Cart cart){
        List<Product> products = cart.getProducts(); //list from cart
        Map<Product, Integer> countedProducts = new TreeMap<>(Comparator.comparing((Product::getId))); //Map(Product, count)
        products.forEach(product -> {
           Integer count = countedProducts.get(product);
            if(count == null)
                countedProducts.put(product, 1);
            else
                countedProducts.replace(product, ++count);
        });

        this.totalPrice = 0;
        countedProducts.forEach((product, count) -> {
           // SortedProduct sortedProduct = new SortedProduct(product, count);
           // sortedProducts.add(sortedProduct);
           // totalPrice += sortedProduct.getAmount();
        });
    }

    public SortedCartResponse(List<SortedProduct> sortedProducts, int totalPrice) {
        this.sortedProducts = sortedProducts;
        this.totalPrice = totalPrice;
    }
}
