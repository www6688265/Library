<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>图书管理系统</title>
    <meta charset="utf-8">
    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="lib/gritter/css/jquery.gritter.css"/>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet">
    <script src="lib/chart-master/Chart.js"></script>
</head>
<body>
<section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>
        <!--logo start-->
        <a href="${pageContext.request.contextPath}/index" class="logo"><b>图书管理<span>系统</span></b></a>
        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><a class="logout" href="${pageContext.request.contextPath}/admin/logOut">退出登录</a></li>
            </ul>
        </div>
    </header>
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">
                <li class="mt">
                    <a href="${pageContext.request.contextPath}/User_table">
                        <i class="fa fa-dashboard"></i>
                        <span>用户管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>图书管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="${pageContext.request.contextPath}/addBook">新书上架</a></li>
                        <li><a href="${pageContext.request.contextPath}/Book_table">图书管理</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Borrow_table">
                        <i class="fa fa-cogs"></i>
                        <span>借书记录查询</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Ticket_table">
                        <i class="fa fa-book"></i>
                        <span>罚单查询</span>
                    </a>
                </li>
            </ul>
        </div>
    </aside>
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-9 main-chart">
                    <!--CUSTOM CHART START -->
                    <div class="border-head">
                        <h3>${admid}欢迎登录图书管理系统</h3>
                    </div>
                    <!-- /row -->
                </div>
               <div class="col-lg-3 ds">
                    <!-- CALENDAR-->
                    <div id="calendar" class="mb">
                        <div class="panel green-panel no-margin">
                            <div class="panel-body">
                                <div id="date-popover" class="popover top"
                                     style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                                    <div class="arrow"></div>
                                    <h3 class="popover-title" style="disadding: none;"></h3>
                                    <div id="date-popover-content" class="popover-content"></div>
                                </div>
                                <div id="my-calendar"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            <p>
                &copy; Copyrights  All Rights Reserved
            </p>
            <a href="index.jsp#" class="go-top">
                <i class="fa fa-angle-up"></i>
            </a>
        </div>
    </footer>

</section>
<!-- js placed at the end of the document so the pages load faster -->
<script src="lib/jquery/jquery.min.js"></script>

<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="lib/jquery.scrollTo.min.js"></script>
<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
<script src="lib/jquery.sparkline.js"></script>
<!--common script for all pages-->
<script src="lib/common-scripts.js"></script>
<script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="lib/gritter-conf.js"></script>
<!--script for this page-->
<script src="lib/sparkline-chart.js"></script>
<script src="lib/zabuto_calendar.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $("#date-popover").popover({
            html: true,
            trigger: "manual"
        });
        $("#date-popover").hide();
        $("#date-popover").click(function (e) {
            $(this).hide();
        });

        $("#my-calendar").zabuto_calendar({
            action: function () {
                return myDateFunction(this.id, false);
            },
            action_nav: function () {
                return myNavFunction(this.id);
            },
            ajax: {
                url: "show_data.php?action=1",
                modal: true
            }
        });
    });

    function myNavFunction(id) {
        $("#date-popover").hide();
        var nav = $("#" + id).data("navigation");
        var to = $("#" + id).data("to");
        console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
</script>
</body>

</html>
