function positif(login){
    $.ajax({
        url : 'Positif',
        type : 'GET',
        data : 'login=' + login,
        dataType : 'html',
        success : function(code_html, statut){
            location.href=("Index");
        }
    });
    location.reload();
}