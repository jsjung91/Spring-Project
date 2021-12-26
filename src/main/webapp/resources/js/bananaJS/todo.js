const toDoForm = document.querySelector("#todo-form");
const toDoInput = toDoForm.querySelector("input"); 
const toDoList = document.querySelector("#todo-list");

let toDos = [];

function saveToDos(){
	localStorage.setItem("todos", JSON.stringify(toDos));
}

function handlerToDoSubmit(e){
	e.preventDefault();
	const newTodo = toDoInput.value;
	toDoInput.value = "";
	const newToDoObj = {
		text : newTodo,
		id : Date.now()
	};
	toDos.push(newToDoObj);
	paintToDo(newToDoObj);
	saveToDos();
}

function deleteTodo(e){
	const li = e.target.parentElement;
	li.remove();
	toDos = toDos.filter((toDo) => toDo.id !== parseInt(li.id));
	saveToDos();
}

function paintToDo(newTodo){
	const li = document.createElement("li");
	li.id = newTodo.id;
	const span = document.createElement("span");
	span.innerText = newTodo.text;
	const button = document.createElement("button");
	button.innerText = "X";
	button.addEventListener("click", deleteTodo);
	li.appendChild(span);
	li.appendChild(button);
	toDoList.appendChild(li);
}

toDoForm.addEventListener("submit", handlerToDoSubmit);

const savedToDos = localStorage.getItem("toDos");

if(savedToDos !== null) {
	const parsedToDos = JSON.parse(savedToDos);
	toDos = parsedToDos;
	parsedToDos.forEach(paintToDo);
}

