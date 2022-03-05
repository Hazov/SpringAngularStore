package ru.voronasever.voronaStore.repositories.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.voronasever.voronaStore.model.Address;
import ru.voronasever.voronaStore.model.User;

@Repository
public interface AddressRepository extends CrudRepository<Address, Short> {
    Address findByPath(String address);

}
