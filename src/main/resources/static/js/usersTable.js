// Call the dataTables jQuery plugin
$(document).ready(function () {
    loadUsers();
    $('#usersTable').DataTable();
    updateUserEmail();
});

function updateUserEmail() {
    document.getElementById('txt-user-email').outerHTML = localStorage.email;
}

async function loadUsers() {
    const request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    });
    const users = await request.json();


    let listHtml = '';
    for (let user of users) {
        let deleteButton = '<a href="#" onclick="deleteUser(' + user.id + ')" class=\"btn btn-danger btn-circle btn-sm\"><i class=\"fas fa-trash\"></i></a>'
        let txtPhone = user.phone == null ? '-' : user.phone;
        let userHtml = '<tr><td>' + user.id + '</td><td>' + user.name + ' </td><td>' + user.email + '</td><td>' + txtPhone + '</td><td>' + deleteButton + '</td></tr>'
        listHtml += userHtml;
    }

    console.log(users);


    document.querySelector('#usersTable tbody').outerHTML = listHtml;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

async function deleteUser(id) {

    if (!confirm('Desea eliminar este usuario?')) {
        return;
    }
    const request = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload()
}
