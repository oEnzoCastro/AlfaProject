var imageInput = document.getElementById("imageFile");
var img = document.getElementById("imageBody");
var fileInfo = document.getElementById("fileInfo");
const form = document.getElementById('readerForm');

var resultText = document.getElementById('resultText')

form.addEventListener('submit', event => {
    event.preventDefault()

    const image = URL.createObjectURL(imageInput.files[0])
    const url = imageInput.files[0]

    const dataImage = new FormData();
    
    var fReader = new FileReader();
    
    var dataBase64;
    
    fReader.readAsDataURL(imageInput.files[0]);
    fReader.onloadend = function(event){
        
        const imageData = new URLSearchParams();
        
        dataBase64 = event.target.result
        dataBase64 = dataBase64.split('base64,')
        dataImage.append('image', dataBase64[1])
        dataImage.append('expiration', 3600)
        
        //console.log(dataBase64[1])
        
        img.src = "https://i.pinimg.com/originals/4d/6e/9d/4d6e9d0c60603ee6722f5f5355ada451.gif";
        resultText.innerHTML = `<img src="https://i.pinimg.com/originals/4d/6e/9d/4d6e9d0c60603ee6722f5f5355ada451.gif" alt="Loading">`
        
        
        fetch('https://api.imgbb.com/1/upload?key=89ce12ac76495502feda95f42a9f500c', {
            method: 'POST',
            body: dataImage
        })
        .then(res => res.json())
        .then(data => {
            img.src = image;
            
            
            imageData.append('imageURL', data.data.url)
            console.log(data.data.url)
            
            fetch('http://localhost:4567/showImgText', {
                method: 'POST',
                body: imageData
            })
            .then(response => response.text())
            .then(dataIMG => {
                console.log(imageData)
                resultText.innerHTML = dataIMG
                console.log(dataIMG)
            })
            
        })
    }

});

