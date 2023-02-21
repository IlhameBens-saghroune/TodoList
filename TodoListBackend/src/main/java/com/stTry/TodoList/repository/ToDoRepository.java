package com.stTry.TodoList.repository;

import com.stTry.TodoList.domain.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository {
    private Integer idCounter = 1;
    private List<TodoItem> todoItems = new ArrayList<>();

    public List<TodoItem> fetchAllTodoItems (){
        if (todoItems.size()==0) {
            TodoItem item1 = new TodoItem();
            item1.setId(idCounter++);
            item1.setIsDone(false);
            item1.setTaskDescription("Task #1");

            todoItems.add(item1);
        }
        return todoItems;
    }

    public TodoItem save( TodoItem todoItem){
        todoItem.setId(idCounter++);
        todoItems. add(todoItem);
        return todoItem;
    }

    public void delete(Integer id) {
        todoItems = todoItems.stream()
                             .filter(todoItem -> !todoItem.getId().equals(id))
                             .collect(Collectors.toList());
    }
}
