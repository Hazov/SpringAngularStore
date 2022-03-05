package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Role;
import ru.voronasever.voronaStore.model.RoleEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {
    Optional<Role> findByName(RoleEnum name);
}
