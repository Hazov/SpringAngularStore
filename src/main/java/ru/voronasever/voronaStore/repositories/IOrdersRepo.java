package ru.voronasever.voronaStore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Order;

@Repository
public interface IOrdersRepo extends CrudRepository<Order, Long> {
}
