<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: jordan
  Date: 29/12/2020
  Time: 01:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <!--
    New Event
    http://www.templatemo.com/tm-486-new-event
    -->
    <title>Le Covid c'est pas très sympa - Amis</title>
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
    <link rel="stylesheet" href="css/style.css">

    <!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Poppins:400,500,600' rel='stylesheet' type='text/css'>

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
                <li><a href="index" class="smoothScroll">Intro</a></li>
                <% if (user != null) { %>
                <li><a href="Activites" class="smoothScroll">Activitées</a></li>
                <li><a href="Friend" class="smoothScroll">Amis</a></li>
                <%   } else { %>
                <li><a href="Inscription" class="smoothScroll">Inscription</a></li>
                <% } %>
            </ul>

        </div>

    </div>
</div>


<!-- =========================
    SPEAKERS SECTION
============================== -->
<section id="register" class="parallax-section">
    <div class="container">
        <div class="row">

            <div class="col-md-12 col-sm-12 wow bounceIn">
                <div class="section-title">
                    <h2>Profil</h2>
                </div>
            </div>
        </div>

        <% if (user != null) { %>

        <div class="wow fadeInUp col-md-12 col-sm-12" data-wow-delay="0.6s" id="profil" style="display: block;">
            <h3> Login :     <%= user.getLogin()%></h3>
            <h3> Nom : <%= user.getLastname() %></h3>
            <h3> Prenom : <%= user.getFirstname() %></h3>
            <h3> Date de naissance : <%= user.getDate()%></h3>

            <a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" id="modifier">MODIFIER</a>
        </div>

        <div class="wow fadeInUp col-md-12 col-sm-12" data-wow-delay="0.6s" id="profilForm" style="display: none;">

            <div class="wow fadeInUp col-md-12 col-sm-12" data-wow-delay="1s">
                <div id="alert">

                </div>
                <form>
                    <input name="login" type="text" class="form-control" id="login" placeholder="Login" value="<%= user.getLogin()%>" required>
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password" value="" required>
                    <input name="lastname" type="text" class="form-control" id="lastname" placeholder="Last Name" value="<%= user.getLastname() %>" required>
                    <input name="firstname" type="text" class="form-control" id="firstname" placeholder="First Name" value="<%= user.getFirstname() %>" required>
                    <input name="birthdate" type="text" class="form-control" id="birthdate" placeholder="02/04/1997" value="<%= user.getDate()%>" required>
                </form>
                <div class="col-md-offset-6 col-md-6 col-sm-offset-1 col-sm-10">
                    <input name="submit" type="submit" class="form-control" id="submit" value="Modifier">
                    <a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" id="annuler">Annuler</a>
                </div>
            </div>
        </div>



        <% } %>


    <div/>
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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="js/jsForPage/profil.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.parallax.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/smoothscroll.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/custom.js"></script>

