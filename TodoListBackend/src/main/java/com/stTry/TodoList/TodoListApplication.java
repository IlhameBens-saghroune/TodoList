package com.stTry.TodoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.stTry.TodoList.domain.TodoItem;

@Component
@EnableAutoConfiguration
@SpringBootApplication
public class TodoListApplication implements CommandLineRunner {
	private Integer idCounter = 1;
	private TodoItem todoItem;
    @Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
        String sql = "INSERT INTO todoitem (id, taskDescription, isDone) VALUES (?, ?, ?)";
		//int result = jdbcTemplate.update(sql, 0, "connection to mySql", true);
		todoItem = new TodoItem();
		while (idCounter>0 && idCounter<10) {
			todoItem.setId(idCounter);
			int result = jdbcTemplate.update(sql, todoItem.getId(), todoItem.getTaskDescription(), todoItem.getIsDone());
			if (result > 0) {
				System.out.println("a new row has been inserted");
			}
			idCounter++;
		}
	}
}
