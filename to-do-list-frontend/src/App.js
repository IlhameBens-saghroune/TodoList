import './App.css';
import {useEffect, useState} from 'react';
//import './components/todoItem.jsx';
import TodoItem from './components/todoItem.jsx';
import Header from './components/Header';
  



const App = () => {
    const [todoItems, setTodoItems] = useState(null);

    useEffect(() => {
      console.log("Hey, I've loaded up");

      if(!todoItems){
        fetch('http://localhost:8080/api/todoItems')
        .then((response) => response.json())
        .then(data => { 
        console.log("Todo items list: ",data);
        setTodoItems(data);
      });}
    }, [todoItems]);

  function addNewItem(){
    fetch('http://localhost:8080/api/todoItems', {
      headers:{
        'content-type' : 'application/json',
      },
      method: "POST",  
  })
  .then((response) => response.json())
  .then((aTodoItem) => {
    // console.log(aTodoItem);
    setTodoItems([...todoItems, aTodoItem]);
  });

  }

  function handleDeleteItem (item) { 
   const updatedItems = todoItems.filter(
    (aTodoItem) => aTodoItem.id !== item.id
    );
    console.log("updated todo list", updatedItems);
   setTodoItems([...updatedItems]);
  }

  return (
    <div className='container'>
      <div className='app-wrapper'>
        <h2>My To-do list:</h2>
        <button onClick={addNewItem}>+ Add New Item</button>
        <div>
      {todoItems 
      ? todoItems.map((todoItem) => {
        return (
          <div>
          <TodoItem 
           key={todoItem.id} 
           data={todoItem} 
           emitDeleteItem = {handleDeleteItem}
          />;  
        </div>
        )
  }) : "loading ..."}
      </div>
      </div>
     
    </div>
    
  );
}

export default App;
