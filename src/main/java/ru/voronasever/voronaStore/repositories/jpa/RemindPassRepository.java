package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Product;
import ru.voronasever.voronaStore.model.RemindPass;

import java.util.Optional;

@Repository
public interface RemindPassRepository extends CrudRepository<RemindPass, Integer> {
    Optional<RemindPass> findByHash(String hash);
}
