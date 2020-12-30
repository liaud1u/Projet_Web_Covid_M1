<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.User" %>
<%@ page import="java.util.ArrayList" %>

<% for(User user : (ArrayList<User>)request.getAttribute("users")) {%>
<div  class="flex-container-item speakers-wrapper">
    <img src="images/user/default.png" class="img-responsive" alt="avatar">
    <div class="speakers-thumb">
        <h3> <%=user.getLogin()%></h3>
        <h6> <%=user.getFirstname()%> <%=user.getLastname()%></h6>
    </div>
</div>
<%     }%>