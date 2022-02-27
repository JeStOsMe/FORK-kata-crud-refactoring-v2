package co.com.sofka.crud.services;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sofka.crud.bases.ToDoListBase;
import co.com.sofka.crud.bases.TodoBase;
import co.com.sofka.crud.models.ToDoList;
import co.com.sofka.crud.models.Todo;
import co.com.sofka.crud.repositories.ToDoListRepository;
import co.com.sofka.crud.repositories.TodoRepository;

@Service
public class ToDoListService {

    public static final String NO_FAULT_ID = "No existe el id de la lista";

    private ToDoListRepository listRepo;
    private TodoRepository todoRepo;

    @Autowired
    public ToDoListService(ToDoListRepository listRepo, TodoRepository todoRepo){
        this.listRepo = listRepo;
        this.todoRepo = todoRepo;
    }

    public Set<TodoBase> getTodosByListId(Long id){
        return listRepo.findById(id)
                .orElseThrow()
                .getTodos().stream()
                .map(item -> new TodoBase(item.getId(), item.getName(), item.isCompleted(), id))
                .collect(Collectors.toSet());
    }

    public TodoBase addNewTodoByListId(Long id, TodoBase todoBase){
        Optional<ToDoList> list = listRepo.findById(id);

        if (list.isPresent()){
            ToDoList _list = list.get();
            Todo todo = new Todo();

            todo.setId(todoBase.getId());
            todo.setName(todoBase.getName());
            todo.setCompleted(todoBase.isCompleted()); 

            if (todo.getName().isEmpty()){
                throw new EmptyStackException();    
            }

            _list.getTodos().add(todo);

            ToDoList listUpdated = listRepo.save(_list);

            Todo lastTodo = listUpdated.getTodos()
                                .stream()
                                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                                .orElseThrow();
            todoBase.setId(lastTodo.getId());
            todoBase.setListId(id);
            return todoBase;

        } else {
            throw new EmptyStackException();
        }
    }

    public TodoBase updateATodoByListId(Long id, TodoBase todoBase){
        Optional<ToDoList> list = listRepo.findById(id);

        if (list.isPresent()){
            ToDoList _list = list.get();

            for (Todo todo: _list.getTodos()){
                if (todo.getId().equals(todoBase.getId())){
                    todo.setId(todoBase.getId());
                    todo.setName(todoBase.getName());
                    todo.setCompleted(todoBase.isCompleted());
                }
            }

            listRepo.save(_list);

            return todoBase;
        } else {
            throw new EmptyStackException();
        }
    }

    public ToDoListBase newToDoList (ToDoListBase listBase){
        ToDoList list = new ToDoList();
        list.setName(listBase.getName());

        if (list.getName().isEmpty()){
            throw new EmptyStackException();
        }

        Long id = listRepo.save(list).getId();

        listBase.setId(id);

        return listBase;
    }

    public Set<ToDoListBase> getAllListToDos(){
        return StreamSupport
                .stream(listRepo.findAll().spliterator(), false)
                .map(todoList -> {
                    Set<TodoBase> list = todoList.getTodos()
                        .stream()
                        .map(item -> new TodoBase(item.getId(), item.getName(), item.isCompleted(), todoList.getId()))
                        .collect(Collectors.toSet());
                    return new ToDoListBase(todoList.getId(), todoList.getName(), list);
                })
                .collect(Collectors.toSet());

    }

    public void deleteListById(Long listId){
        Optional<ToDoList> list = listRepo.findById(listId);

        if (list.isPresent()){
            ToDoList _list = list.get();
            listRepo.delete(_list);
        }
    }

    public void deleteATodoById(Long id){
        Optional<Todo> todo = todoRepo.findById(id);

        if (todo.isPresent()){
            Todo _todo = todo.get();
            todoRepo.delete(_todo);
        }
    }
    
}
