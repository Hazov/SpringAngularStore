package ru.voronasever.voronaStore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Review;

@Repository
public interface IReviewRepo extends CrudRepository<Review, Long> {
}
