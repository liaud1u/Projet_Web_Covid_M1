function selectionLieu(name) {
    $.ajax({
        url : 'SelectionLieu',
        type : 'GET',
        data : 'name=' + name,
        dataType : 'html',
        success : function(code_html, statut){
            location.href=("Index");
        }
    });
}