package co.com.sofka.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.sofka.crud.models.ToDoList;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Long>{
    
}
