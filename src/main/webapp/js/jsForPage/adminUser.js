function init() {
    $("#searchuser").on("input", searchuser);
    $("#userList").on("change", fillInput);
    $("#infos").on("click", afficheInfos);
}

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
    $("#profil").css("display", "block");
    let login = $("#searchuser").val();
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getUser", login: login},
        success: (response) => {
           fillProfil(response.split(';'));
           afficheFriend(login);
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}



function fillProfil(user) {
    let login = $("<h3></h3>").text("Login :" + user[0]);
    let nom = $("<h3></h3>").text("Nom :" + user[1]);
    let prenom = $("<h3></h3>").text("Prenom :" + user[2]);
    let date = $("<h3></h3>").text("Date de naissance :" + user[3]);
    $("#profil").empty();
    $("#profil").append(login, nom, prenom, date);
}

function afficheFriend(user) {
    $("#friends").css("display", "block");
    let login = user;
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllFriend", login: login},
        success: (response) => {
            let friend = response.split('/');
           friend.forEach(name => affichageList(name));
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



$(document).ready(function () {
    init();
    searchuser();
})