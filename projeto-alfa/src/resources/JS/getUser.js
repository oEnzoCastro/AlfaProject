
fetch("http://localhost:4567/getAll")
.then(res => res.json())
.then(data => {
    var users = document.getElementById("userTable")
    users.innerHTML += data
})

// Erro de acesso