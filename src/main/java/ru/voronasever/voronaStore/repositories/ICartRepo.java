package ru.voronasever.voronaStore.repositories;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Cart;
import ru.voronasever.voronaStore.model.User;

@Repository
public interface ICartRepo extends CrudRepository<Cart, Integer> {

    Cart getCartById(int Id);


}
