function init() {
    $("#searchLieu").on("input", searchLieu);
    $("#lieuList").on("change", fillInput);
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

$(document).ready(function () {
    init();
    searchLieu();
})