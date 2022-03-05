package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
