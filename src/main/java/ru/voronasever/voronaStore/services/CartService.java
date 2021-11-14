package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.repositories.ICartRepo;

import java.util.List;

@Service
public class CartService {
    @Autowired
    ICartRepo cartRepository;

    @Transactional
    public void addProductToCart(Cart cart, Product product){
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Cart cart, Product productReq){
        List<Product> cartProducts = cart.getProducts();
        int id = productReq.getId();
        cartProducts.removeIf(p -> p.getId()==id);
        cartRepository.save(cart);
    }

    public Cart getCartById(int Id) {
      return cartRepository.getCartById(Id);

    }

    public void removeOneProductFromCart(Cart userCart, Product product) {
        List<Product> cartProducts = userCart.getProducts();
        int id = product.getId();
        for (Product p : cartProducts) {
            if(p.getId() == id){
                cartProducts.remove(p);
                break;
            }
        }
        cartRepository.save(userCart);
    }
}
