package ru.voronasever.voronaStore.payload.response;

import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;

import java.util.*;

public class SortedCartWithAmountResponse {
    Product[] products;
    int[] counts;
    int[] amounts;
    int totalSum;

    SortedCartWithAmountResponse(Cart cart){
        Map<Product, ArrayList<Integer>> countedProducts = new TreeMap<>(Comparator.comparing((Product::getId))); //Map(Product, count, amount)
        List<Product> products = cart.getProducts();
        products.forEach(product -> {
            ArrayList<Integer> countsAndAmounts = countedProducts.get(product);
            Integer count = countsAndAmounts.get(0);
            if(countsAndAmounts.get(0) == null){ //get count
                ArrayList<Integer> countAndAmount = new ArrayList<>();
                countAndAmount.add(0,1);//count = 1
                countAndAmount.add(1,product.getPrice()); //price = 1 x price
                countedProducts.put(product, countAndAmount);
            }
            else{
                countsAndAmounts.add(0, ++count); //count+1
                countsAndAmounts.add(1, count * product.getPrice()); //price = newCount x price
                countedProducts.replace(product, countsAndAmounts);
            }
        });

        int size = countedProducts.size();
        this.products = new Product[size];
        this.counts = new int[size];
        this.amounts = new int[size];
        this.totalSum = 0;

        int count = 0;
        countedProducts.forEach((product, countsAndAmounts) -> {
            this.products[count] = product;
            this.counts[count] = countsAndAmounts.get(0);
            this.amounts[count] = countsAndAmounts.get(1);
            this.totalSum+=this.amounts[count];
        });
    }
}
