package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.Order;
import ru.voronasever.voronaStore.repositories.jpa.OrdersRepository;

@Service
public class OrderService {
    @Autowired
    OrdersRepository ordersRepository;

    public void createNewOrder(Order order) {
        ordersRepository.save(order);
    }
}
