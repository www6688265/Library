<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>图书馆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Custom Theme files -->
    <link href="front/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <!-- shop css -->
    <link href="front/css/shop.css" type="text/css" rel="stylesheet" media="all">
    <!-- flexslider-css -->
    <link rel="stylesheet" href="front/css/flexslider.css" type="text/css" media="screen"/>
    <link href="front/css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- font-awesome icons -->
    <link href="front/css/font-awesome.css" rel="stylesheet">
    <!-- //Custom Theme files -->
    <!-- online-fonts -->
    <!-- logo -->
    <link href="//fonts.googleapis.com/css?family=Fredericka+the+Great" rel="stylesheet">
    <!-- titles -->
    <link href="//fonts.googleapis.com/css?family=Merriweather+Sans:300,300i,400,400i,700,700i,800,800i"
          rel="stylesheet">
    <!-- body -->
    <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
          rel="stylesheet">
    <!-- //online-fonts -->
</head>


<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<div id="home">
    <!-- header -->
    <!-- navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="main-nav">
            <div class="container">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-ex1-collapse">
                        <span class="sr-only">图书馆</span>
                    </button>
                    <h1>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/frontIndex">图书馆</a>
                    </h1>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse nav-right">
                    <ul class="nav navbar-nav navbar-left cl-effect-15">
                        <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                        <li class="hidden">
                            <a class="page-scroll" href="#page-top"></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/frontIndex">首页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/BookList">所有图书</a>
                        </li>
                        <c:if test="${!empty userid}">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/logOut">退出登录</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pageContext.request.contextPath}/profile" title="SignIn & SignUp">
                                <span class="fa fa-user nav-icon" aria-hidden="true"></span>
                            </a>
                        </li>
                    </ul>
                    <!-- search-bar -->
                    <div class="search-bar-agileits">
                        <div class="cd-main-header">
                            <ul class="cd-header-buttons">
                                <li>
                                    <a class="cd-search-trigger" href="#cd-search">
                                        <span></span>
                                    </a>
                                </li>
                            </ul>
                            <!-- cd-header-buttons -->
                        </div>
                        <div id="cd-search" class="cd-search">
                            <form action="${pageContext.request.contextPath}/BookList" method="get">
                                <input name="bookname" type="search" placeholder="输入图书名字,按回车搜索">
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.navbar-collapse -->
                <div class="clearfix"></div>
            </div>
            <!-- /.container -->
        </div>
    </nav>
    <!-- //banner -->
    <!-- breadcrumbs -->
    <div class="crumbs text-center">
        <div class="container">
            <div class="row">
                <ul class="btn-group btn-breadcrumb bc-list">
                    <li class="btn btn1">
                        <a href="${pageContext.request.contextPath}/frontIndex">
                            <i class="glyphicon glyphicon-home"></i>
                        </a>
                    </li>
                    <li class="btn btn2">
                        <a href="${pageContext.request.contextPath}/profile">登录与注册</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--//breadcrumbs ends here-->
    <!-- signin and signup form -->
    <div class="login-form section text-center">
        <div class="container">
            <h4 class="rad-txt">
                <span class="abtxt1">登录</span>
            </h4>
            <div id="loginbox" style="margin-top:30px;" class="mainbox  loginbox">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">登录</div>
                    </div>
                    <div style="padding-top:30px" class="panel-body">
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                        <form id="loginform" class="form-horizontal" method="post">
                            <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                <input id="login-username" type="text" class="form-control" name="idcard"
                                       placeholder="身份证" required="">
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-lock"></i>
                                    </span>
                                <input id="login-password" type="password" class="form-control" name="password"
                                       placeholder="密码" required="">
                            </div>
                            <center>
                                <div id="errorMsg" style="color: red;">

                                </div>
                            </center>
                            <div style="margin-top:10px" class="form-group">
                                <!-- Button -->
                                <div class="col-sm-12 controls">
                                    <button type="button" id="btn-login" class="btn btn-success">登录</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                        没有账号？
                                        <span>
                                            请携带身份证至图书馆注册账号
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--//signin and signup form ends here-->
<!-- //footer -->
<!-- //home -->
<!-- js -->
<script src="front/js/jquery-2.2.3.min.js"></script>
<!-- //js -->
<!--search jQuery-->
<script src="front/js/main.js"></script>
<!--search jQuery-->
<!-- cart-js -->
<script src="front/js/minicart.js"></script>
<script>
    chr.render();

    chr.cart.on('new_checkout', function (evt) {
        var items, len, i;

        if (this.subtotal() > 0) {
            items = this.items();

            for (i = 0, len = items.length; i < len; i++) {
            }
        }
    });
</script>
<!-- //cart-js -->

<!-- dropdown nav -->
<script>
    $(document).ready(function () {
        $(".dropdown").hover(
            function () {
                $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                $(this).toggleClass('open');
            },
            function () {
                $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                $(this).toggleClass('open');
            }
        );
    });
</script>
<!-- //dropdown nav -->

<!-- Scrolling Nav JavaScript -->
<script src="front/js/scrolling-nav.js"></script>
<!-- //fixed-scroll-nav-js -->
<!-- start-smooth-scrolling -->
<script src="front/js/move-top.js"></script>
<script src="front/js/easing.js"></script>
<script>
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();

            $('html,body').animate({
                scrollTop: $(this.hash).offset().top
            }, 1000);
        });
    });
</script>
<!-- //end-smooth-scrolling -->
<!-- smooth-scrolling-of-move-up -->
<script>
    $(document).ready(function () {
        $().UItoTop({
            easingType: 'easeOutQuart'
        });
        $("input").on("keydown", function (e) {
            if (e.keyCode == 13) {
                $("#btn-login").trigger("click");
            }
        });

        $("#btn-login").click(function () {
            $("#errorMsg").html("");
            if ($("#login-username").val() === "" || $("#login-password").val() === "") {
                $("#errorMsg").html('<lable class="error">用户名或密码不能为空 </lable> ');
                return;
            }
            $.ajax({
                url: "/user/login",
                type: "POST",
                dataType: "json",
                data: $("#loginform").serialize(),
                success: function (data) {
                    if (data.result == "success") {
                        window.location.href = "${pageContext.request.contextPath}/profile";
                    }
                    else {
                        alert(data.result);
                    }
                },
                error: function () {
                    alert("网络出现问题！");
                }
            })
        });
    });
</script>
<script src="front/js/SmoothScroll.min.js"></script>
<script src="front/js/bootstrap.js"></script>
</body>

</html>