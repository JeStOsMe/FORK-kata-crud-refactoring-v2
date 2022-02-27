package co.com.sofka.crud.bases;

import java.util.Set;

public class ToDoListBase {
    
    private Long id;
    
    private String name;

    private Set<TodoBase> todos;

    
    public ToDoListBase() {
    }    

    public ToDoListBase(Long id, String name, Set<TodoBase> todos) {
        this.id = id;
        this.name = name;
        this.todos = todos;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TodoBase> getTodos() {
        return todos;
    }

    public void setTodos(Set<TodoBase> todos) {
        this.todos = todos;
    }

    
}
