<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.User" %>
<%@ page import="SQLPackage.SQLConnector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <!--
    New Event
    http://www.templatemo.com/tm-486-new-event
    -->
    <title>StopCovid - Amis</title>
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

</head>
<body data-spy="scroll" data-offset="50" data-target=".navbar-collapse">

<%
    User user1 = (User) session.getAttribute("user");
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
                <% if (user1 != null) { %>
                <li><a href="Amis" class="smoothScroll">Mes amis</a></li>
                <li><a href="Activites" class="smoothScroll">Activitées</a></li>
                <li><a href="./profile.html" class="smoothScroll">Profil</a></li>
                <button class="btn btn-default dropdown-toggle mr-4 float-right" type="button" onclick="location.href = 'Notifications';">
                    <img src="images/notif.png" alt="notification" width="20" height="20">
                    <span class="badge badge-pill "><%= user1.getNotificationsNonLues().size() %></span>
                </button>
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
<section id="speakers" class="parallax-section">
    <div class="container">
        <div class="row">

            <div class="col-md-12 col-sm-12 wow bounceIn">
                <div class="section-title">
                    <h2>Ajouter des amis</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <h4>Rechercher un membre (par pseudo):</h4>
            <div class="row justify-content-center">
                <input class="barre-recherche site-search" type="search" id="site-search" name="q"
                       placeholder="Rechercher un membre"
                       onkeyup="searchUser(document.getElementById('site-search').value);">

            </div>
        </div>


        <div class="flex-container" id="liste">
                <% for(User user : (ArrayList<User>)request.getAttribute("users")) { %>
                <div  class="flex-container-item speakers-wrapper">
                    <img src="images/user/default.png" class="img-responsive" alt="avatar" width="100">
                    <div class="speakers-thumb">
                        <h3> <%=user.getLogin()%>
                            <%if(!user1.hasFriend(user.getLogin())){%>
                            <img src="images/friends/add-friend.png" class="img-responsive" alt="ajouter amis" width="40" style="display: inline-table;" onclick="ajoutAmi('<%=user.getLogin()%>',document.getElementById('site-search').value)">
                            <%}%>
                        </h3>
                        <h6> <%=user.getFirstname()%> <%=user.getLastname()%></h6>
                    </div>
                </div>
                <%     }%>


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
<script src="js/jsForPage/rechercheMembre.js"></script>
<script src="js/jsForPage/ajoutAmi.js"></script>


</body>
</html>