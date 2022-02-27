package co.com.sofka.crud.bases;

public class TodoBase {
    
    private Long id;
    private Long listId;
    private String name;
    private boolean isCompleted;
    
    public TodoBase() {
    }
    
    public TodoBase(Long id, String name, boolean isCompleted, Long listId) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
        this.listId = listId;
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
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public Long getListId() {
        return listId;
    }
    
    public void setListId(Long listId) {
        this.listId = listId;
    }
}
