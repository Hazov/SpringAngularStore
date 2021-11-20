package ru.voronasever.voronaStore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.voronasever.voronaStore.model.Address;
import ru.voronasever.voronaStore.model.User;

public interface IAddressRepo extends CrudRepository<Address, Short> {
    Address findByPath(String address);

}
