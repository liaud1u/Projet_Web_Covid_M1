function init() {
    $("#submit").on('click', connexion);
}


function connexion() {
    const login = $("#login").val();
    const password = $("#password").val();


    var href = window.location.href
    const alertNotif = $('#alert');
    $.ajax({
        type: 'POST',
        url: href,
        data : {login: login, password: password},
        timeout: 5000,
        success: (response) => {
            if (response === "true") {
                alertNotif.empty();
                alertNotif.removeClass('alert alert-danger');
                alert("Connexion réussit");
            } else {
                alertNotif.addClass('alert alert-danger');
                alertNotif.empty()
                alertNotif.append("Login ou mot de passe incorrect !");
            }
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })

}










$(document).ready(function () {
    init();
})