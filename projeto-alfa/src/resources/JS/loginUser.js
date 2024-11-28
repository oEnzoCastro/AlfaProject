console.log(localStorage)
const form = document.querySelector('.form');

form.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = new URLSearchParams(formData);
    
    fetch('http://https://6748b2c02e8d244a8033b552--projeto-alfa.netlify.app:4567/loginUsuario', {
        method: 'POST',
        body: data
    })
    .then(res => res.json())
    .then(data => {
        if (data > 0) {
            localStorage.setItem('userId', data)
            window.location.href = "./index.html"
        } else {
            document.getElementById('loginError').innerHTML = "Login Incorreto!"
        }
    })
    .catch(error => console.log(error));
});
