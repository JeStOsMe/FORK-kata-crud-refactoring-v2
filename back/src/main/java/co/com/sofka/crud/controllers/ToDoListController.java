package co.com.sofka.crud.controllers;

import java.util.EmptyStackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.crud.bases.ToDoListBase;
import co.com.sofka.crud.bases.TodoBase;
import co.com.sofka.crud.services.ToDoListService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoListController {
    
    @Autowired
    private ToDoListService listService;

    @GetMapping("api/list")
    public Iterable<ToDoListBase> getAllListTodos(){
        return listService.getAllListToDos();
    }

    @GetMapping("api/{listId}/todos")
    public Iterable<TodoBase> getTodosByListId(@PathVariable("listId") Long id){
        return listService.getTodosByListId(id);
    }

    @PostMapping("api/todolist")
    public ToDoListBase newListTodo(@RequestBody ToDoListBase list){
        return listService.newToDoList(list);
    }

    @DeleteMapping("api/{listId}/todolist")
    public void deleteListById(@PathVariable("listId") Long id){
        listService.deleteListById(id);
    }

    @PutMapping("api/{listid}/todo")
    public TodoBase updateATodoByListId(@PathVariable("listid") Long id, @RequestBody TodoBase todo){
        if (todo.getId() != null){
            return listService.updateATodoByListId(id, todo);
        }
        throw new EmptyStackException();
    }

    @PostMapping("api/{listid}/todo")
    public TodoBase addNewTodoByListId(@PathVariable("listid") Long id, @RequestBody TodoBase todo){
        return listService.addNewTodoByListId(id, todo);
    }

    @DeleteMapping("api/{id}/todoil")
    public void deleteATodoById(@PathVariable("id") Long id){
        listService.deleteATodoById(id);
    }

}
