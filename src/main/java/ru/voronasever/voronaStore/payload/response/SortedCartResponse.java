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
    List<SortedProduct> sortedProducts;
    int totalPrice;

    public SortedCartResponse(List<SortedProduct> sortedProducts, int totalPrice) {
        this.sortedProducts = sortedProducts;
        this.totalPrice = totalPrice;
    }
}
