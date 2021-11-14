package ru.voronasever.voronaStore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.voronasever.voronaStore.model.Address;

public interface IAddressRepo extends CrudRepository<Address, Short> {

}
