package ru.voronasever.voronaStore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.payload.response.SortedProduct;

import java.util.List;

@Repository
public interface ICartRepo extends CrudRepository<Cart, Integer> {

    Cart getCartById(int Id);

    @Query(value = "SELECT SUM(product_price) FROM products\n" +
            "INNER JOIN carts_products cp on products.product_id = cp.product_id\n" +
            "WHERE cart_id = ?1",
            nativeQuery = true)
    Integer getTotalPrice(Integer id);

    @Query(value = "SELECT p.*,\n" +
            "       p.product_id          AS id,\n" +
            "       p.product_article     AS article,\n" +
            "       p.product_name        AS name,\n" +
            "       p.product_description AS description,\n" +
            "       p.product_price       AS price,\n" +
            "       p.product_count       AS countInStock,\n" +
            "       p.product_reviews     AS feedbacks,\n" +
            "       COUNT(cp.product_id)  AS countSelected,\n" +
            "       SUM(product_price)    as amount\n" +
            "from products as p\n" +
            "         INNER JOIN carts_products cp on p.product_id = cp.product_id\n" +
            "WHERE cart_id = ?1\n" +
            "GROUP BY p.product_id", nativeQuery = true)

    List<SortedProduct> getSortedProducts(Integer cartId);

}
