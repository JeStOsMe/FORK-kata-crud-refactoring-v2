package co.com.sofka.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.sofka.crud.models.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
