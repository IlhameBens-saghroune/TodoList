import React from "react";
import {useEffect, useState} from 'react';

const TodoItem = (props) => {
    const {emitDeleteItem} = props;
    const [todoItem, setTodoItem] = useState(props.data);
    const [isDirty, setDirty] = useState(false);

    useEffect(() => {
        if (isDirty) {
           fetch(`http://localhost:3000/api/todoItems/${todoItem.id}`, {
             method: "PUT",
             headers: {
                 'content-type': 'application/json',
            },
             body: JSON.stringify(todoItem),
        })
        .then(response => response.json())
        .then((data) => {
            setDirty(false);
            setTodoItem(data);
        });
    }
    }, [todoItem, isDirty]);

    function updateTask(e) {
        setDirty(true);
        setTodoItem({...todoItem, taskDescription: e.target.value});
    }
   
    function deleteItem(){
        fetch(`http://localhost:3000/api/todoItems/${todoItem.id}`, {
             method: 'DELETE',
             headers: {
                 'content-type': 'application/json',
            },
            // body: JSON.stringify
        })
        .then(response => {
            emitDeleteItem(todoItem); 
        });

    }

    return (
    <>
        <input 
            type="checkbox" 
            checked={todoItem.isDone} 
            onChange={() => {
                setDirty(true);
                setTodoItem({... todoItem, isDone: !todoItem.isDone});
            }} 
        />{" "}
        {todoItem.isDone ? (
            <span>{todoItem.taskDescription}</span>
        ) : (
            <input 
            type = 'text' 
            value = {todoItem.getTaskDescription} 
            onChange={(e) => {
                updateTask(e);
            }}/>
        )}
        <span 
          style={{marginLeft:'1rem', cursor: 'pointer'}} 
          onClick={deleteItem}>
            🗑️
        </span>
    </>      
    );
};

export default TodoItem;