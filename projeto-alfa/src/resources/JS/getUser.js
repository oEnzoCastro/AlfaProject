var user = document.getElementById("user")

function setLogin() {
    if (localStorage.length < 1) {
        localStorage.setItem("user", user.value)
    } else {
        localStorage.clear
        localStorage.setItem("user", user.value)
    }
}

console.log(localStorage)
