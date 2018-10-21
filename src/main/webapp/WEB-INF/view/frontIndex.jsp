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
    <div class="banner-bg-agileits">
        <!-- banner-text -->
        <div class="banner-text">
            <div class="container">
                <p class="b-txt">Best</p>
                <h2 class="title">
                    图书馆
                </h2>
                <ul class="banner-txt">
                    <li>分享.</li>
                    <li> 探索.</li>
                    <li>充实.</li>
                </ul>
            </div>
        </div>
        <!-- //banner-text -->
    </div>
    <!-- //footer -->
    <div class="cpy-right">
        <p>© 2018 copyright
        </p>
    </div>
</div>
<!-- //home -->
<!-- js -->
<script src="front/js/jquery-2.2.3.min.js"></script>
<!-- //js -->
<!--search jQuery-->
<script src="front/js/main.js"></script>
<!--search jQuery-->
<!-- Scrolling Nav JavaScript -->
<script src="front/js/scrolling-nav.js"></script>
<!-- //fixed-scroll-nav-js -->
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
        /*
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        */

        $().UItoTop({
            easingType: 'easeOutQuart'
        });

        $("")

    });
</script>
<script src="front/js/SmoothScroll.min.js"></script>
<!-- //smooth-scrolling-of-move-up -->
<!-- about bottom grid Numscroller -->
<script src="front/js/numscroller-1.0.js"></script>
<!-- //about bottom grid Numscroller -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="front/js/bootstrap.js"></script>
</body>

</html>