package ru.voronasever.voronaStore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.RemindPass;

import java.util.Optional;

public interface IRemindPassRepo extends CrudRepository<RemindPass, Integer> {
    Optional<RemindPass> findByHash(String hash);
}
