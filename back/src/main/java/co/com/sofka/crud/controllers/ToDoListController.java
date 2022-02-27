package co.com.sofka.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.crud.bases.ToDoListBase;
import co.com.sofka.crud.services.ToDoListService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoListController {
    
    @Autowired
    private ToDoListService listService;

}
