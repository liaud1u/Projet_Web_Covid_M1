<%@ page import="bean.User" %>
<!DOCTYPE html>
        <html lang="fr">
        <head>
            <!--
            New Event
            http://www.templatemo.com/tm-486-new-event
            -->
            <title>StopCovid - Erreur 404</title>
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
     NAVIGATION LINKS
============================== -->
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
                <li><a href="Activites" class="smoothScroll">Activitées</a></li>
                <li><a href="Profil" class="smoothScroll">Profil</a></li>
                <button class="btn btn-default dropdown-toggle mr-4 float-right" type="button" onclick="location.href = 'Notifications';">
                    <img src="images/notif.png" alt="notification" width="20" height="20">
                    <span class="badge badge-pill "><%= user1.getNotificationsNonLues().size() %></span>
                </button>
                <%
                if(!user1.isPositif()){%>
                <a class="btn btn-lg btn-danger smoothScroll wow fadeInUp" data-wow-delay="2.3s" onclick="if(confirm('Confirmez vous être positif à la Covid19 ?')){positif('<%=user1.getLogin()%>')}">JE SUIS POSITIF</a>
                <%}%><%   } else { %>
                <li><a href="Inscription" class="smoothScroll">Inscription</a></li>
                <% } %>
            </ul>

        </div>

    </div>
</div>



<!-- =========================
    404 SECTION
============================== -->
<section id="intro" class="parallax-section">
    <div class="container">
        <div class="row">

            <img src="images/not-found/traffic-cone.svg" class="img-responsive" style="width: 30%;display: block;margin-left: auto;margin-right: auto;">
            <h1>Erreur 404 !</h1>

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