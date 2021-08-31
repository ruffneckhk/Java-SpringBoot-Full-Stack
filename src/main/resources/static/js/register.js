$(document).ready(function () {
    //on ready

});

async function registerUsers() {
    let data = {};
    data.name = document.getElementById('txtName').value;
    data.email = document.getElementById('txtEmail').value;
    data.password = document.getElementById('txtPassword').value;

    let repeatPassword = document.getElementById('txtRepeatPassword').value;

    if (repeatPassword != data.password) {
        alert('El password no coincide');
        return;
    }
    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    alert("La cuenta fue creada con exito");
    window.location.href = 'login.html'
}
