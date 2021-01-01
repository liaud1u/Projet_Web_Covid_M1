function accepterNotif(notification){
    $.ajax({
        url : 'AccepterNotif',
        type : 'GET',
        data : 'id=' + notification,
        dataType : 'html',

        success : function(code_html, statut){
            location.reload()
        }
    });
}

function refuserNotif(notification){
    $.ajax({
        url : 'RefuserNotif',
        type : 'GET',
        data : 'id=' + notification,
        dataType : 'html',
        success : function(code_html, statut){
            location.reload()
        }
    });
}
