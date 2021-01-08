function init() {
    $("#searchLieu").on("input", searchLieu);
    $("#lieuList").on("change", fillInput);
    $("#infos").on("click", afficheInfos);
}

let lieu = {nom: "", adresse: "", id: ""};

function searchLieu() {
    const lieu = $("#searchLieu").val();
    var href = window.location.href
    $("#lieuList").empty();
    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllLieu", lieu: lieu},
        success: (response) => {
            let lieuList = response.split('|');
           if ($("#searchLieu").val() === "") {
               createOption(" ");
           }
           lieuList.forEach(name => createOption(name));
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }
    })
}

function createOption(name) {
    if (name !== "") {
        let o = new Option(name, name);
        $(o).html(name);
        $("#lieuList").append(o);
    }
}

function fillInput() {
    let name = $("#lieuList option:selected").text();
    $("#searchLieu").val(name);
    searchLieu();
}

function afficheInfos() {
    $("#infoLieu").css("display", "block");
    let name = $("#searchLieu").val();
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getLieu", name: name},
        success: (response) => {
           let res = response.split('|');
           lieu.nom = res[0];
           lieu.adresse = res[1];
           lieu.id = res[2];
           fillLieu();
           afficheActivite();
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}

function fillLieu() {
    let name = $("<h3></h3>").text("Nom: " + lieu.nom);
    let adr = $("<h3></h3>").text("Adresse: " + lieu.adresse);
    $("#lieu").empty();
    $("#lieu").append(name, adr);
}

function afficheActivite() {
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllActivite", id: lieu.id},
        success: (response) => {
            $("#listActivite").empty();
           let activite = response.split('|');
           activite.forEach(activite => affichageList(activite));
           $("#modif").remove();
           $("#infoLieu").append('<a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" onclick="modif()" id="modif">Modifier les informations</a>');
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}

function affichageList(activite) {
    if (activite !== "") {
        let date = activite.split(";");
        let txt = "Date de debut: " + date[0] + "  | Date de fin: " + date[1] + " | Utilisateur: " + date[2];
        let li = $("<li></li>").text(txt);
        $("#listActivite").append(li);
    }
}

function modif() {
    $("#infoLieu").css("display", "none");
    $("#modifLieu").css("display", "block");

    $("#nomForm").val(lieu.nom);
    $("#adresseForm").val(lieu.adresse);
}

function modifier() {
    let nom = $("#nomForm").val();
    let adresse = $("#adresseForm").val();

    let href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "modifier", nom: nom, adresse: adresse, id: lieu.id},
        timeout: 5000,
        success: (response) => {
            if(response==="true") {
                $("#modifLieu").css("display", "none");
                $("#searchLieu").val(nom);
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

function annuler() {
    $("#modifLieu").css("display", "none");
    $("#infoLieu").css("display", "block");
}

function supprimer() {
    var href = window.location.href

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "delete", id: lieu.id},
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
    searchLieu();
})