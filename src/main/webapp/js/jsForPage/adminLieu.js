function init() {
    $("#searchLieu").on("input", searchLieu);
    $("#lieuList").on("change", fillInput);
    $("#infos").on("click", afficheInfos);
}

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
    $("#lieu").css("display", "block");
    let name = $("#searchLieu").val();
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getLieu", name: name},
        success: (response) => {
           let res = response.split('|');
           fillLieu(res);
           afficheActivite(res[2]);
        },
        error: (xhr) => {
            console.log("status = " + xhr.status);
            console.log(xhr);
        }

    })
}

function fillLieu(lieu) {
    let name = $("<h3></h3>").text("Nom: " + lieu[0]);
    let adr = $("<h3></h3>").text("Adresse: " + lieu[1]);
    $("#lieu").empty();
    $("#lieu").append(name, adr);
}

function afficheActivite(id) {
    $("#activites").css("display", "block");
    var href = window.location.href;

    $.ajax({
        type: 'POST',
        url: href,
        data: {type: "getAllActivite", id: id},
        success: (response) => {
           let activite = response.split('|');
           activite.forEach(activite => affichageList(activite));
           $("#infoLieu").append('<a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" id="modif">Modifier les informations</a>');
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

$(document).ready(function () {
    init();
    searchLieu();
})