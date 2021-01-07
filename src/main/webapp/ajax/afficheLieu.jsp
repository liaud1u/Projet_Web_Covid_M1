<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Lieu" %>

<% for(Lieu lieu : (ArrayList<Lieu>)request.getAttribute("lieux")) { %>
<div  class="flex-container-item speakers-wrapper">
    <div class="speakers-thumb">
        <h3> <%=lieu.getName()%></h3>
        <h6> <%=lieu.getAdresse().replace(",","<br>")%></h6>
        <button  class="btn btn-lg smoothScroll" onclick="selectionLieu('<%=lieu.getName()%>')">Choix du lieu</button>

    </div>
</div>
<%     }%>

<div  class="flex-container-item speakers-wrapper">
    <div class="speakers-thumb">
        <h3> Ajouter un
            nouveau lieu</h3>

        <form method="get" action="AjouterLieu">
            <input name="nom" class="form-control" type="text" id="nom" placeholder="Nom" value="Faculté des Sciences et Techniques de Nancy (FST)" required="true">
            <input name="adresse" class="form-control"  type="text" id="adresse" placeholder="Adresse" value="Campus, Boulevard des Aiguillettes, 54506 Vandœuvre-lès-Nancy" required="true">

            <div class="col-md-offset-6 col-md-6 col-sm-offset-1 col-sm-10">
                <button name="submit" type="submit" id="submit" class="btn btn-lg smoothScroll" >Nouveau Lieu</button>
            </div>
        </form>


    </div>
</div>