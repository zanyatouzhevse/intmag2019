package start.domain.repository;


import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;
import start.domain.Item;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Item, Integer> {
    Item findByName(String name);
    Item findById(int id);
    @Transactional
    void deleteByName (String name);

}