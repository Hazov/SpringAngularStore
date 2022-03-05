package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
