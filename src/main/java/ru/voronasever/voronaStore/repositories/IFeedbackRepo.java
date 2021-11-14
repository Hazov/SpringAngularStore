package ru.voronasever.voronaStore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Feedback;

@Repository
public interface IFeedbackRepo extends CrudRepository<Feedback, Long> {
}
