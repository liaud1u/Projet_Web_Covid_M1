function init() {
    $("#submit").on('click', connexion);
}


function connexion() {
    const login = $("#login").val();
    const password = $("#password").val();


    var href = window.location.href

    $.ajax({
        type: 'POST',
        url: href,
        data : {login: login, password: password},
        timeout: 5000,
        success: (response) => {
            console.log(response);
            if (response === "true") {
                alert("Connexion réussit");
                console.log(sessionStorage.getItem("user"));
            } else {
                console.log("Mot de passe ou login non correct");
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