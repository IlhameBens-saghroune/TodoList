package com.stTry.TodoList.service;


import com.stTry.TodoList.domain.TodoItem;
import com.stTry.TodoList.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository todoRepo;
    public List<TodoItem> fetchAllTodoItems (){
        return todoRepo.fetchAllTodoItems();
    }

    public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
        Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
                                             .stream()
                                             .filter(item -> item.getId().equals(id))
                                             .findAny();
        if (todoOpt.isPresent()) {
            TodoItem item = todoOpt.get();
            item.setIsDone(todoItem.getIsDone());
            item.setTaskDescription((todoItem.getTaskDescription()));
            return item;
        }
        return null;
    }

    public TodoItem createItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setIsDone(false);
        todoItem = todoRepo.save(todoItem);
        todoItem.setTaskDescription("Task #" + todoItem.getId());
        return todoItem;
    }

    public void deleteTodoItem(Integer id) {
        todoRepo.delete(id);
    }
}
