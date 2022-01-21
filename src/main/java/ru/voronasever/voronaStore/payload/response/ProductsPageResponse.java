package ru.voronasever.voronaStore.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.voronasever.voronaStore.model.Product;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsPageResponse {
    List<Product> products;
    int pagesCount;
}
