const modal = document.getElementById("modal");

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
        alert("할 일이 삭제되었습니다.");
        location.reload();
    }).catch((error) => console.log(error));
}

function completeTodo(id) {
    const url = "/api/todo/complete/"+id;

    fetch(url, {
        method: "POST",
    }).then((response) => response.json()
    ).then((data) => {
        alert("할 일이 완료되었습니다.");
        location.reload();
    }).catch((error) => console.log(error));
}

function updateTodo() {
    const id = document.getElementById('todo-id').value;
    const url = "/api/todo/"+id;

    let data = {
        content: document.getElementById('todo-update').value,
        completed_fl: false,
    };

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => response.json()
    ).then((data) => {
        alert("수정 되었습니다.");
        location.reload();
    }).catch((error) => console.log(error));
}

function openModal(id) {
    document.getElementById('todo-id').value = id;

    modal.classList.remove("hidden");
}

function closeModal() {
    document.getElementById('todo-id').value = '';
    modal.classList.add("hidden");
}