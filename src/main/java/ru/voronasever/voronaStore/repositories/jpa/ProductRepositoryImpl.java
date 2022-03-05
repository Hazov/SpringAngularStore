package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.repositories.jpa.ProductRepository;
import ru.voronasever.voronaStore.repositories.jpa.ProductRepositoryCustom;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;


public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    @Lazy
    ProductRepository productRepository;  /* Optional - if you need it */

    @Override
    public void saveAllOnConflictDoNothing(List<Product> productList) {
        StringBuilder saveQuery = new StringBuilder();
        saveQuery.append("INSERT INTO products" +
                "(product_article," +
                " product_name," +
                " product_description," +
                " product_price," +
                " product_category," +
                " product_count) " +
                "VALUES ");
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            saveQuery.append("(");
            saveQuery.append("'").append(currentProduct.getArticle()).append("', ");
            saveQuery.append("'").append(currentProduct.getName()).append("', ");
            saveQuery.append("'").append(currentProduct.getDescription()).append("', ");
            saveQuery.append(currentProduct.getPrice()).append(", ");
            saveQuery.append(currentProduct.getCategory().getId()).append(", ");
            saveQuery.append(currentProduct.getCountOnStock());
            saveQuery.append(")");
            if(iterator.hasNext())
                saveQuery.append(", ");
        }
        saveQuery.append(" ON CONFLICT DO NOTHING ");
        System.out.println(saveQuery);
        em.createNativeQuery(saveQuery.toString()).executeUpdate();

    }

}
