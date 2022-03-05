package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.payload.response.SortedCartResponse;
import ru.voronasever.voronaStore.payload.response.SortedProduct;
import ru.voronasever.voronaStore.repositories.jpa.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;

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
    public Cart getCart(){
        return getCartByUser(userService.getCurrentUser());
    }

    public Cart getCart(User user){
        return getCartByUser(user);
    }

    Cart getCartByUser(User user){
        return getCartById(user.getCart().getId());
    }

    public int getTotalPrice(Cart cart) {
        return cartRepository.getTotalPrice(cart.getId());
    }

    public void resetCart(Cart cart) {
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
    }
    public SortedCartResponse getSortedCart(){
        Cart cart = getCart();
        int totalPrice = 0;
        List<SortedProduct> sortedProducts = cartRepository.getSortedProducts(cart.getId());
        for (SortedProduct s:sortedProducts)
            totalPrice += s.getAmount();
        return new SortedCartResponse(sortedProducts, totalPrice);

    }
}
