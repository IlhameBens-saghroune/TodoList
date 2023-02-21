package com.stTry.TodoList.web;

import com.stTry.TodoList.domain.TodoItem;
import com.stTry.TodoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    @Autowired
    private ToDoService todoServ;
    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems (){
         List<TodoItem> todoItems = todoServ.fetchAllTodoItems();
         return ResponseEntity.status(HttpStatus.OK).body(todoItems);
    }
    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewItem() {
         TodoItem todoItem = todoServ.createItem();

         return ResponseEntity.ok(todoItem);
    }

    @DeleteMapping("/api/todoItems/{id}")
    public ResponseEntity<?> deleteTodoItem (@PathVariable Integer id){
        todoServ.deleteTodoItem(id);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/api/todoItems/{id}")
    public ResponseEntity<?> updateTodoItem (@PathVariable Integer id, @RequestBody TodoItem todoItem){
       TodoItem updatedTodoItem = todoServ.updateTodoItem(id,  todoItem);
       return ResponseEntity.ok(updatedTodoItem);
    }

}
