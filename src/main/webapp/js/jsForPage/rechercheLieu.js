function searchLieu(name) {
    $.ajax({
        url : 'RechercheLieu',
        type : 'GET',
        data : 'critaire=' + name,
        dataType : 'html',
        success : function(code_html, statut){
            $("#liste").html(code_html);
        }
    });
}