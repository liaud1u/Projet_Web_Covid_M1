function init() {

    $("#login").on('input', verifyLogin);
    $("#password").on('input', verifyPassword);
    $("#lastname").on('input', verifyName);
    $("#firstname").on('input', verifyName);
    $("#birthdate").on('input', verifyDate);
    $("#submit").on('click', inscription);
}

function verifyLogin() {

    /* Regex pour le login
        - doit contenir au moins 4 caractères
        - ne peut pas commencer par un chiffre
        - ne peut pas contenir d'espace
     */
    const regexLogin = RegExp('^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,19}$');
    const login = $(this).val();

    if (!regexLogin.test(login)) {
        $(this).css("color", "red");

    } else {
        $(this).css("color", 'white');

    }
}

function verifyPassword() {

    /* Regex pour le mot de passe
        - doit contenir au moins 8 caractère
        - doit contenir 1 lettre minuscule, 1 lettre majuscule, 1 chiffre
        - peut contenir des caractères spéciaux
     */
    const regexPassword = RegExp('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$');
    const password = $(this).val();

    if (!regexPassword.test(password)) {
        $(this).css("color", "red");

    } else {

        $(this).css("color", "white");
    }

}

function verifyName() {

    /* Regex pour Name
        - doit commencer par un majuscule
        - ne peut pas contenir de chiffre
        - ne peut pas contenir de caractère spéciaux à part le tiré
        - après un espace ou un tiré il faut une majuscule
     */
    const regexName = RegExp('^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$');
    const name = $(this).val();

    if (!regexName.test(name)) {

        $(this).css("color", "red");
    } else {

        $(this).css("color", "white");
    }

}

function verifyDate() {

    /*Regex pour la date
        - format DD/MM/YYYY
        - verifie que le 29 fevrier correpond bien année bisextile
     */
    const regexDate = RegExp('^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00))$');
    const date = $(this).val();

    if (!regexDate.test(date)) {

        $(this).css("color", "red");
    } else {

        $(this).css("color", "white");
    }

}

function inscription() {
    const login = $("#login").val();
    const password = $("#password").val();
    const firstname = $("#firstname").val();
    const lastname = $("#lastname").val();
    const birthdate = $("#birthdate").val();

    var href = window.location.href


    $.ajax({
        type: 'POST',
        url: href,
        data : {login: login, password: password, lastname: lastname, firstname: firstname, birthdate: birthdate},
        timeout: 5000,
        success: (response) => {
           if(response==="true") {
               href = href.substr(0, href.indexOf('/Inscription')) + "/Connexion";
               window.location = href;
           } else {
               const alert = $('#alert');
               alert.addClass("alert alert-danger");
               alert.empty();
               alert.append("Ce login n'est pas disponible !");
           }
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })

}





$( function() {
    $( "#birthdate" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yy'
    });

} );

$(document).ready(function () {
    init();
})