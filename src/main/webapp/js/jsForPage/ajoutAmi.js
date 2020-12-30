function ajoutAmi(login,critaire){
    $.ajax({
        url : 'AjoutAmi',
        type : 'GET',
        data : 'nom=' + login,
        dataType : 'html'
    });

    $.ajax({
        url : 'RechercheMembre',
        type : 'GET',
        data : 'critaire=' + critaire,
        dataType : 'html',
        success : function(code_html, statut){
            $("#liste").html(code_html);
        }
    });
}