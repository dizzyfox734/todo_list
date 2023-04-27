function saveTodo() {
    let data = {
        content: document.getElementById('todo-save').value,
        completed_fl: false,
    };

    fetch("/api/todo", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => response.json()
    ).then((data) => {
        location.reload();
    }).catch((error) => console.log(error));
}

function deleteTodo(id) {
    const url = "/api/todo/"+id;

    fetch(url, {
        method: "DELETE",
    }).then((response) => response.json()
    ).then((data) => {
        location.reload();
    }).catch((error) => console.log(error));
}
