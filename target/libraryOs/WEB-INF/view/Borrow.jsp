<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>用户管理</title>

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
</head>
<script type="text/javascript" language="javascript" src=https://code.jquery.com/jquery-3.3.1.js></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>


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
                <li><a class="logout" href="login.jsp">退出登录</a></li>
            </ul>
        </div>
    </header>
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">
                <li class="mt">
                    <a class="active" href="${pageContext.request.contextPath}/User_table">
                        <i class="fa fa-group"></i>
                        <span>用户管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-barcode"></i>
                        <span>图书管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="${pageContext.request.contextPath}/addBook">新书上架</a></li>
                        <li><a href="${pageContext.request.contextPath}/Book_table">图书管理</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Borrow_table">
                        <i class="fa fa-tasks"></i>
                        <span>借书记录查询</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Ticket_table">
                        <i class="fa fa-ticket"></i>
                        <span>罚单查询</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Borrow">
                        <i class="fa fa-book"></i>
                        <span>借书</span>
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
        <section class="wrapper site-min-height">
            <h3><i class="fa fa-angle-right"></i>借书</h3>
            <div class="row mt">
                <div class="col-md-12">
                    <div class="col-md-12 mb">
                        <div class="message-p pn">
                            <div class="message-header">
                                <h5>请输入用户身份证</h5>
                            </div>
                            <div class="row">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/people.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <form id="idcardForm"class="form-inline" role="form">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="idcard" name="idcard"
                                                   placeholder="身份证">
                                        </div>
                                        <button id="idSubmit" type="button" class="btn btn-default">下一步</button>
                                    </form>
                                </div>
                            </div>
                            <div id="overLimitInfo" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p><b id="username2"></b>超过借书数量借书，暂不能借书</p>
                                </div>
                            </div>
                            <div id="TicketNotDeal" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p><b id="username3"></b>有罚单未处理，金额为：<b id="fee"></b>元</p>
                                    <button id="dealSubmit" type="button" class="btn btn-default">罚单已处理</button>
                                </div>
                            </div>
                            <div id="userNameInfo" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/prompt.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>该用户名为：<b id="username1"></b></p>
                                </div>
                            </div>
                            <div id="userNotFound" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>该用户为新用户，请注册后借书</p>
                                </div>
                            </div>
                            <div id="hasOverDueBook" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p><b id="username5"></b>有超过借书期限图书，暂不能借书</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div id="bookDiv" class="row mt" style="display: none">
                <div class="col-md-12">
                    <div class="col-md-12 mb">
                        <div class="message-p pn">
                            <div class="message-header">
                                <h5>请输入图书ISBN编号</h5>
                            </div>
                            <div class="row">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/book.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <form id="bookForm" class="form-inline" role="form">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="isbn" name="isbn"
                                                   placeholder="ISBN">
                                        </div>
                                        <button id="bookSubmit" type="button" class="btn btn-default">下一步</button>
                                    </form>
                                </div>
                            </div>
                            <div id="bookNotFound" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>未找到该图书，请重新输入</p>
                                </div>
                            </div>
                            <div id="confirm" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="img/prompt.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>该图书名为：《<b id="bookName1"></b>》</p>
                                    <button type="button" class="btn btn-default">确认借书</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="display: block">
                <form id="borrowForm">
                    <input id="userid" name="userid">
                    <input id="bookid" name="bookid">
                </form>
            </div>
        </section>
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            <p>
                &copy; Copyrights All Rights Reserved
            </p>
        </div>
    </footer>
    <!--footer end-->
</section>
<!-- js placed at the end of the document so the pages load faster -->

<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="lib/jquery.scrollTo.min.js"></script>
<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
<script src="lib/common-scripts.js"></script>
<!--script for this page-->
<script src="lib/form-validation-script.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#idSubmit").click(function () {
            $.ajax({
                url:"/borrow/userCheck",
                type:"POST",
                dataType:"json",
                data:$("#idcardForm").serialize(),
                success:function (data) {
                    switch(data.result)
                    {
                        case "1":
                            $("#username1").html(data.username);
                            $("#userid").val(data.userid);
                            $("#userNameInfo").show();
                            $("#overLimitInfo").hide();
                            $("#TicketNotDeal").hide();
                            $("#userNotFound").hide();
                            $("#hasOverDueBook").hide();
                            $("#bookDiv").show();
                            break;
                        case "2":
                            $("#username2").html(data.username);
                            $("#userNameInfo").hide();
                            $("#overLimitInfo").show();
                            $("#TicketNotDeal").hide();
                            $("#userNotFound").hide();
                            $("#hasOverDueBook").hide();
                            $("#bookDiv").hide();
                            break;
                        case "3":
                            $("#username1").html(data.username);
                            $("#username3").html(data.username);
                            $("#userid").val(data.userid);
                            $("#fee").html(data.fee);
                            $("#userNameInfo").hide();
                            $("#overLimitInfo").hide();
                            $("#TicketNotDeal").show();
                            $("#userNotFound").hide();
                            $("#hasOverDueBook").hide();
                            $("#bookDiv").hide();
                            break;
                        case "4":
                            $("#userNameInfo").hide();
                            $("#overLimitInfo").hide();
                            $("#TicketNotDeal").hide();
                            $("#userNotFound").show();
                            $("#hasOverDueBook").hide();
                            $("#bookDiv").hide();
                            break;
                        case "5":
                            $("#username5").html(data.username);
                            $("#userNameInfo").hide();
                            $("#overLimitInfo").hide();
                            $("#TicketNotDeal").hide();
                            $("#userNotFound").hide();
                            $("#hasOverDueBook").show();
                            $("#bookDiv").hide();
                            break;
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        });
        $("#dealSubmit").click(function () {
            $.ajax({
                url:"/borrow/dealTicket",
                type:"POST",
                dataType:"json",
                data:$("#borrowForm").serialize(),
                success:function (data) {
                    if(data.result=="success"){
                        $("#userNameInfo").show();
                        $("#overLimitInfo").hide();
                        $("#TicketNotDeal").hide();
                        $("#userNotFound").hide();
                        $("#bookDiv").show();
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        });
        $("#bookSubmit").click(function () {
            $.ajax({
                url:"/borrow/bookCheck",
                type:"POST",
                dataType:"json",
                data:$("#bookForm").serialize(),
                success:function (data) {
                    if(data.result=="1"){
                        $("#bookName1").html(data.bookname);
                        $("#bookid").val(data.bookid);
                        $("#bookNotFound").hide();
                        $("#confirm").show();
                    }
                    else{
                        $("#bookNotFound").show();
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        });

        $("#confirm").click(function () {
            $.ajax({
                url:"/borrow/borrowBook",
                type:"POST",
                dataType:"json",
                data:$("#borrowForm").serialize(),
                success:function (data) {
                    if(data.result=="success"){
                        alert("借书成功");
                        $("#confirm").hide();
                        $("#bookDiv").hide();
                        $("#userNameInfo").hide();
                        $("#idcard").val("");
                        $("#isbn").val("");
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        });
    });
</script>
</body>


</html>
