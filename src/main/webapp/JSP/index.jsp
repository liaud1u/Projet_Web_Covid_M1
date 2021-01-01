<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: jordan
  Date: 29/12/2020
  Time: 01:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <!--
    New Event
    http://www.templatemo.com/tm-486-new-event
    -->
    <title>StopCovid</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/owl.theme.css">
    <link rel="stylesheet" href="css/owl.carousel.css">

    <!-- Main css -->
    StopCovid    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">


<!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Poppins:400,500,600' rel='stylesheet' type='text/css'>


    <link rel="shortcut icon" href="images/favicon.ico"  type="image/x-icon">
</head>
<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">

<%
    User user = (User) session.getAttribute("user");
%>

<!-- =========================
     PRE LOADER
============================== -->
<div class="preloader">

    <div class="sk-rotating-plane"></div>

</div>


<!-- =========================
     NAVIGATION LINKS
============================== -->
<div class="navbar navbar-fixed-top custom-navbar" role="navigation">
    <div class="container">

        <!-- navbar header -->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse">

            <ul class="nav navbar-nav navbar-right">

                <% if (user != null) { %>
                <li><a href="AjouterAmi" class="smoothScroll">Ajouter des amis</a></li>
                <li><a href="Amis" class="smoothScroll">Mes amis</a></li>
                <li><a href="Activites" class="smoothScroll">Activitées</a></li>
                <li><a href="Profil" class="smoothScroll">Profil</a></li>
                <button class="btn btn-default dropdown-toggle mr-4 float-right" type="button" onclick="location.href = 'Notifications';">
                    <img src="images/notif.png" alt="notification" width="20" height="20">
                    <span class="badge badge-pill "><%= user.getNotificationsNonLues().size() %></span>
                </button>
                <%
                    if(!user.isPositif()){%>
                <a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" onclick="if(confirm('Confirmez vous être positif à la Covid19 ?')){positif('<%=user.getLogin()%>')}">JE SUIS POSITIF</a>
                <%}%>
                <%   } else { %>
                <li><a href="Inscription" class="smoothScroll">Inscription</a></li>
                <li><a href="Connexion" class="smoothScroll">Connexion</a></li>
                <% } %>

            </ul>

        </div>

    </div>
</div>


<!-- =========================
    INTRO SECTION
============================== -->
<section id="intro" class="parallax-section">
    <div class="container">
        <div class="row">

            <div class="col-md-12 col-sm-12">
                <h1 class="wow fadeInUp" data-wow-delay="1.6s">Stop Covid</h1>

                <%if(session.getAttribute("user")==null){%>
                <a href="Connexion" class="btn btn-lg btn-default smoothScroll wow fadeInUp hidden-xs" data-wow-delay="2.3s">Se connecter</a>
                <a href="Inscription" class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s">S'inscrire</a>
                <%}else{
                    if(!user.isPositif()){%>
                <a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" onclick="if(confirm('Confirmez vous être positif à la Covid19 ?')){positif('<%=user.getLogin()%>')}"><h1>JE SUIS POSITIF</h1></a>
                <%}else{%>

                <h2 class="wow fadeInUp" data-wow-delay="1.6s">Vous vous êtes déclaré positif, tout vos amis et cas contacts ont été prévenu.
                    Merci de votre participation et bon rétablissement.</h2>
                <%}
                }
                %>
            </div>


        </div>
    </div>
</section>




<!-- =========================
    FOOTER SECTION
============================== -->
<footer>
    <div class="container">
        <div class="row">

            <div class="col-md-12 col-sm-12">
                <p class="wow fadeInUp" data-wow-delay="0.6s">Copyright &copy; 2020 LIAUD & SCHERRING

                    | Design: <a rel="nofollow" href="http://www.templatemo.com/page/1" target="_parent">Templatemo</a></p>

                <ul class="social-icon">
                    <li><a href="#" class="fa fa-facebook wow fadeInUp" data-wow-delay="1s"></a></li>
                    <li><a href="#" class="fa fa-twitter wow fadeInUp" data-wow-delay="1.3s"></a></li>
                    <li><a href="#" class="fa fa-google-plus wow fadeInUp" data-wow-delay="2s"></a></li>
                </ul>

            </div>

        </div>
    </div>
</footer>


<!-- Back top -->
<a href="#back-top" class="go-top"><i class="fa fa-angle-up"></i></a>


<!-- =========================
     SCRIPTS
============================== -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.parallax.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/smoothscroll.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/jsForPage/positif.js"></script>


</body>
</html>
