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
    <!-- checkout css -->
    <link href="front/css/checkout.css" type="text/css" rel="stylesheet" media="all">
    <!-- Range-slider-css -->
    <link rel="stylesheet" type="text/css" href="front/css/jquery-ui1.css">
    <!-- common-css -->
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
    <style type="text/css">
        .dd {
            width: 180px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            course: hand;
            -webkit-line-clamp: 2;
        }
    </style>
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
    <!-- //navbar ends here -->
    <!-- banner -->
    <div class="banner-bg-inner">
        <!-- banner-text -->
        <div class="banner-text-inner">
            <div class="container">
                <h2 class="title-inner">
                    <c:choose>
                        <c:when test="${empty param.type}">
                            所有图书
                        </c:when>
                        <c:otherwise>
                            ${param.typename}
                        </c:otherwise>
                    </c:choose>

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
                        <a href="index.html">
                            <i class="glyphicon glyphicon-home"></i>
                        </a>
                    </li>
                    <li class="btn btn2">
                        <a href="${pageContext.request.contextPath}/BookList">所有图书</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <ul class="nav nav-pills" id="Types">
                    <li><a href="${pageContext.request.contextPath}/BookList">所有图书</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!--//breadcrumbs ends here-->
    <!-- Shop -->
    <div class="innerf-pages section">
        <div class="container-cart">
            <!-- product left -->
            <div class="side-bar col-md-3">
                <!-- // preference -->
                <div class="search-hotel">
                    <h3 class="shopf-sear-headits-sear-head">
                        <span>条件</span> 搜索</h3>
                    <form action="${pageContext.request.contextPath}/BookList" method="get">
                        <input type="search" placeholder="作者" name="author">
                        <span>&nbsp;</span>
                        <input type="search" placeholder="图书名字" name="bookname">
                        <span>&nbsp;</span>
                        <input type="submit" value="搜索">
                    </form>
                </div>
            </div>
            <!-- //product left -->
            <!-- product right -->
            <div class="left-ads-display col-md-9">
                <div class="wrapper_top_shop">
                    <!-- product-sec1 -->
                    <div class="product-sec1" id="BookList">
                        <!-- row1-->
                        <!-- //row3 -->
                    </div>
                    <div class="clearfix"></div>
                    <div class="clearfix">
                        <ul id='pageInfo'></ul>
                    </div>


                </div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>

</div>
</div>
<!-- //footer -->
<div class="cpy-right">
    <p>© 2018 Chronicle. All rights reserved | Design by
        <a href="http://w3layouts.com"> W3layouts.</a>
    </p>
</div>
</div>
<!-- //home -->
<!-- js -->
<!-- Common js -->
<script src="front/js/jquery-2.2.3.min.js"></script>
<!--// Common js -->
<!-- cart-js -->
<script src="front/js/minicart.js"></script>
<script src="front/js/bootstrap-paginator.js"></script>
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
<!-- price range (top products) -->
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<!-- //price range (top products) -->

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
        $.ajax({
            url: "/book/getAllTypes",
            success: function (data) {
                for (var type of data)
                    $("#Types").append("<li><a href=\"${pageContext.request.contextPath}/BookList?type=" + type.id + "&typename=" + type.booktype + "\">" + type.booktype + "</a></li>")
            }
        })

        var element = $("#pageInfo");
        var pageSize = 12;
        var bookname = "";
        var author = "";
        var booktype = "";
        bookname += "${param.bookname}";
        author += "${param.author}";
        booktype += "${param.type}";
        pageStart();

        function pageStart() {
            $.ajax({
                type: "post",
                url: "/book/getList",
                dataType: "json",
                data: {
                    "page": 1,
                    "pageSize": pageSize,
                    "bookname": bookname,
                    "author": author,
                    "type": booktype
                },
                success: function (data) {
                    for (var item of data.list) {
                        if (item.pic === null)
                            item.pic = "";
                        $("#BookList").prepend(`
                        <div class="col-md-3 product-men">
                            <div class="product-chr-info chr">
                                <div class="thumbnail">
                                    <a href="${pageContext.request.contextPath}/BookInfo?bookid=` + item.bookid + `">
                                        <img src="` + item.pic + `"onerror="this.src='/front/images/errorImg.jpg'">
                                    </a>
                                </div>
                                <div class="caption" style="max-height:50px">
                                    <h4 class="dd">` + item.bookname + `</h4>
                                    <p>作者：` + item.author + `</p>
                                </div>
                            </div>
                        </div>`)
                    }
                    ;
                    var options = {
                        bootstrapMajorVersion: 3, //bootstrap的版本要求
                        currentPage: data.page,//当前页数
                        totalPages: data.pages,//总页数
                        numberOfPages: data.pageSize,//每页记录数
                        itemTexts: function (type, page, current) {//设置分页按钮显示字体样式
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },
                        onPageClicked: function (event, originalEvent, type, page) {//分页按钮点击事件
                            $.ajax({
                                url: "/book/getList",
                                type: "post",
                                dataType: "json",
                                data: {
                                    "page": page,
                                    "pageSize": pageSize,
                                    "bookname": bookname,
                                    "author": author,
                                    "type": booktype
                                },
                                success: function (data) {
                                    $("#BookList").html("");
                                    for (var item of data.list) {
                                        if (item.pic === null)
                                            item.pic = "";
                                        $("#BookList").prepend(`
                        <div class="col-md-3 product-men">
                            <div class="product-chr-info chr">
                                <div class="thumbnail">
                                    <a href="${pageContext.request.contextPath}/BookInfo?bookid=` + item.bookid + `">
                                        <img src="` + item.pic + `"onerror="this.src='/front/images/errorImg.jpg'">
                                    </a>
                                </div>
                                <div class="caption" style="max-height:50px">
                                    <h4 class="dd">` + item.bookname + `</h4>
                                    <p>作者：` + item.author + `</p>
                                </div>
                            </div>
                        </div>`);
                                    }

                                }
                            })
                        }
                    };
                    //初始化分页框
                    element.bootstrapPaginator(options);
                }
            })
        }

        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<!-- //here ends scrolling icon -->
<!-- smoothscroll -->
<script src="front/js/SmoothScroll.min.js"></script>
<!-- //smoothscroll -->

</body>

</html>