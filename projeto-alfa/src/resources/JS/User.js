if (localStorage.getItem('userId') < 0 || localStorage.getItem('userId') == undefined ) {
    window.location.href = "./login.html"
}

const userId = document.getElementById('userId')    
userId.value = localStorage.getItem('userId')
const data = new URLSearchParams(localStorage);

const username = document.getElementById('username')

fetch('https://6748b2c02e8d244a8033b552--projeto-alfa.netlify.app:4567/getUsuario', {
    method: 'POST',
    body: data
})
.then(res => res.json())
.then(data => {
    console.log(data.user)
    username.innerHTML = data.user
})

function logout(){
    localStorage.setItem('userId', -1)
    window.location.href = "./login.html"
}

function storeId() {
    const updateId = document.getElementById("updateId")
    console.log(localStorage.userId)
    updateId.value = localStorage.userId
}
