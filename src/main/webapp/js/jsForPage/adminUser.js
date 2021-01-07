function init() {
    $("#searchuser").on("input", searchuser);
    $("#userList").on("change", fillInput);
    $("#infos").on("click", afficheInfos);
}

let user = {login: "", nom: "", prenom: "", date: ""};

function searchuser() {
    const login = $("#searchuser").val();
    var href = window.location.href
    $("#userList").empty();
    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllUser", login: login},
        success: (response) => {
            var userList = response.split('/');
            userList.forEach(name => createOption(name));
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })
}

function createOption(name){
    if (name !== "") {
        let o = new Option(name, name);
        $(o).html(name);
        $("#userList").append(o);
    }
}

function fillInput() {
    let name = $("#userList option:selected").text();
    $("#searchuser").val(name);
    searchuser();
}

function afficheInfos() {
    $("#infoUser").css("display", "block");
    let login = $("#searchuser").val();
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getUser", login: login},
        success: (response) => {
           let res = response.split(';');
           user.login = res[0];
           user.nom = res[1];
           user.prenom = res[2];
           user.date = res[3];
           fillProfil();
           afficheFriend(login);
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}

function fillProfil() {
    let login = $("<h3 id='login'></h3>").text("Login: " + user.login);
    let nom = $("<h3 id='nom'></h3>").text("Nom: " + user.nom);
    let prenom = $("<h3 id='prenom'></h3>").text("Prenom: " + user.prenom);
    let date = $("<h3 id='date'></h3>").text("Date de naissance: " + user.date);
    $("#profil").empty();
    $("#profil").append(login, nom, prenom, date);
}

function afficheFriend(user) {
    let login = user;
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllFriend", login: login},
        success: (response) => {
            $("#listFriend").empty();
            let friend = response.split('/');
           friend.forEach(name => affichageList(name));
           $("#modif").remove();
           $("#infoUser").append('<a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" onclick="modif()" id="modif">Modifier les informations</a>')
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}

function affichageList(friend) {
    let li = $("<li></li>").text(friend);
    $("#listFriend").append(li);
}

function modif() {
    $("#infoUser").css("display", "none");
    $("#modifUser").css("display", "block");

    $("#loginform").val(user.login);
    $("#lastname").val(user.nom);
    $("#firstname").val(user.prenom);
    $("#birthdate").val(user.date);

}

function annuler() {
    $("#modifUser").css("display", "none");
    $("#infoUser").css("display", "block");
}

function modifier() {
    const login = $("#loginform").val();
    const firstname = $("#firstname").val();
    const lastname = $("#lastname").val();
    const birthdate = $("#birthdate").val();

    var href = window.location.href


    $.ajax({
        type: 'POST',
        url: href,
        data : {type: "modifProfil", login: login,  lastname: lastname, firstname: firstname, birthdate: birthdate, ancienLogin: user.login},
        timeout: 5000,
        success: (response) => {
            console.log(response);
            if(response==="true") {
                $("#modifUser").css("display", "none");
                afficheInfos();
            } else {
                const alert = $('#alert');
                alert.addClass("alert alert-danger");
                alert.empty();
                alert.append("Modification impossible !");
            }
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })
}

function supprimer() {
    var href = window.location.href

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "delete", login: user.login},
        timeout: 5000,
        success: () => {
            window.location = href;
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })
}



$(document).ready(function () {
    init();
    searchuser();
})