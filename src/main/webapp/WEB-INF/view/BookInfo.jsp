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
                        <li>
                            <a href="${pageContext.request.contextPath}/UserLogin" title="SignIn & SignUp">
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
    <!-- //navbar ends here -->
    <!-- banner -->
    <div class="banner-bg-inner">
        <!-- banner-text -->
        <div class="banner-text-inner">
            <div class="container">
                <h2 class="title-inner">
                    图书信息
                </h2>

            </div>
        </div>
        <!-- //banner-text -->
    </div>
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
                        <a href="${pageContext.request.contextPath}/BookList">所有图书</a>
                    </li>
                    <li class="btn btn3" id="typeNav">

                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--//breadcrumbs ends here-->
    <!-- Single -->
    <div class="innerf-pages section">
        <div class="container">
            <div class="col-md-4 single-right-left ">
                <div class="grid images_3_of_2">
                    <div class="flexslider1">
                        <div class="thumb-image" id="pic">

                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-8 single-right-left simpleCart_shelfItem">
                <h3 id="bookname">书名：
                </h3>
                <p>作者：
                    <a id="author"></a>
                </p>

                <div class="desc_single">
                    <h5>图书简介</h5>
                    <p id="brief"></p>
                </div>
                <div class="occasional">
                    <h5>详细信息</h5>
                    <ul class="single_specific">
                        <li id="press">
                            <span>出版社 : </span>
                        </li>
                        <li id="type">
                            <span>类型 : </span>
                        </li>
                        <li id="total">
                            <span>库存 : </span>
                        </li>
                        <li id="left">
                            <span>剩余 : </span>
                        </li>
                        <li id="isbn">
                            <span>isbn编号 : </span>
                        </li>
                        <li id="loc">
                            <span>位置 : </span>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
    <!--// Single -->
    <!-- footer -->
    <!-- //footer -->
    <div class="cpy-right">
        <p>© 2018 Chronicle. All rights reserved | Design by
            <a href="http://w3layouts.com"> W3layouts.</a>
        </p>
    </div>
</div>
<!-- //home -->
<!-- Common js -->
<script src="front/js/jquery-2.2.3.min.js"></script>
<!--// Common js -->
<!-- zoom -->
<script src="front/js/imagezoom.js"></script>
<!-- zoom-->
<!-- single -->
<!-- FlexSlider -->
<script src="front/js/jquery.flexslider.js"></script>
<script>
    // Can also be used with $(document).ready()
    $(window).load(function () {
        $('.flexslider1').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });
</script>
<!-- //FlexSlider-->

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
<!--search jQuery-->
<script src="front/js/main.js"></script>
<!--search jQuery-->

<!-- Scrolling Nav JavaScript -->
<script src="front/js/scrolling-nav.js"></script>
<!-- //fixed-scroll-nav-js -->
<!--//scripts-->
<script src="front/js/bootstrap.js"></script>
<!-- start-smoth-scrolling -->
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
<!-- start-smoth-scrolling -->
<!-- here stars scrolling icon -->
<script>
    $(document).ready(function () {
        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<!-- //here ends scrolling icon -->
<!-- smoothscroll -->
<script src="front/js/SmoothScroll.min.js"></script>
<!-- //smoothscroll -->
<script>
    $(document).ready(function () {
        $.ajax({
            url: "/book/getBook?id=" +${param.bookid},
            success: function (data) {
                $("#typeNav").append(data.type);
                $("#bookname").append(data.bookname);
                $("#author").append(data.author);
                $("#press").append(data.press);
                $("#type").append(data.type);
                $("#left").append(data.left);
                $("#brief").append(data.brief);
                $("#total").append(data.total);
                $("#isbn").append(data.isbn);
                $("#loc").append(data.floor + "楼，第" + data.bookcase + "个书架，第" + data.level + "层");
                $("#pic").append(`<img src="` + data.pic + `" data-imagezoom="true" class="img-responsive" onerror="this.src='/front/images/errorImg.jpg'">`);
            }
        })
    });
</script>
</body>
</html>