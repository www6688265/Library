<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>图书管理系统</title>

    <!-- Favicons -->
    <link href="background/img/favicon.png" rel="icon">
    <link href="background/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap core CSS -->
    <link href="background/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="background/lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="background/css/style.css" rel="stylesheet">
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
                <li class="sub-menu">
                    <a class="active" href="${pageContext.request.contextPath}/Return">
                        <i class="fa fa-book"></i>
                        <span>还书</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Admin_table">
                        <i class="fa fa-group"></i>
                        <span>管理员管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Admin_changePwd">
                        <i class="fa fa-key"></i>
                        <span>修改密码</span>
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
            <h3><i class="fa fa-angle-right"></i>还书</h3>
            <div class="row mt">
                <div class="col-md-12">
                    <div class="col-md-12 mb">
                        <div class="message-p pn">
                            <div class="message-header">
                                <h5>请输入用户身份证</h5>
                            </div>
                            <div class="row">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/people.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <form id="idcardForm" class="form-inline" role="form">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="idcard" name="idcard"
                                                   placeholder="身份证">
                                        </div>
                                        <button id="idSubmit" type="button" class="btn btn-default">下一步</button>
                                    </form>
                                </div>
                            </div>
                            <div id="userNameInfo" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/prompt.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>该用户名为：<b id="username1"></b></p>
                                </div>
                            </div>
                            <div id="userNotFound" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>没有找到该用户，请重新输入身份证</p>
                                </div>
                            </div>
                            <div id="notNeedToReturn" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p><b id="username2"></b>没有需要归还的图书</p>
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
                                    <img src="background/img/book.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <form id="bookForm" class="form-inline" role="form">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="isbn_1" name="isbn"
                                                   placeholder="请输入ISBN编号">
                                        </div>
                                        <a href="#" class="removeclass">×</a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3"></div>
                                <div class="col-md-6">
                                    <button id="AddMoreFileBox" type="button" class="btn btn-info">添加图书</button>
                                    <button id="bookSubmit" type="button" class="btn btn-default">下一步</button>
                                </div>
                            </div>
                            <div id="bookNotFound" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/warn.png" class="img-circle" width="40">
                                </div>
                                <div class="col-md-9">
                                    <p>编号为<b id="bookNotFonudisbns"></b>图书未找到，请重新输入</p>
                                </div>
                            </div>
                            <div id="confirm" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/prompt.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>该图书名为：<b id="bookNames"></b></p>
                                    <button id="confirmButton" type="button" class="btn btn-default">确认还书</button>
                                </div>
                            </div>
                            <div id="orderNotFound" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/warn.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>还书失败，该用户没有isbn为<b id="orderNotFoundList"></b>的借书记录</p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div style="display: none">
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

<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="background/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="background/lib/jquery.scrollTo.min.js"></script>
<script src="background/lib/jquery.nicescroll.js" type="text/javascript"></script>
<script src="background/lib/common-scripts.js"></script>
<!--script for this page-->
<script src="background/lib/form-validation-script.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#idSubmit").click(function () {
            var MaxInputs;
            var InputsWrapper = $("#bookForm");
            var borrowForm = $("#borrowForm");
            var x = InputsWrapper.length;
            var FieldCount = 1;

            $("#AddMoreFileBox").click(function (e) {
                if (x < MaxInputs) {
                    FieldCount++;
                    $(InputsWrapper).append("<div class=\"form-group\">\n" +
                        "                                            <input type=\"text\" class=\"form-control\" id=\"isbn_" + FieldCount + "\" name=\"isbn\"\n" +
                        "                                                   placeholder=\"请输入ISBN编号\">\n" +
                        "                                        </div>\n" +
                        "                                        <a href=\"#\" class=\"removeclass\">×</a>");
                    x++;
                    $(borrowForm).append("<input id=\"bookid_" + FieldCount + "\" name=\"bookid\">");
                }
                else {
                    alert("该用户最多可以还" + MaxInputs + "本书！");
                }
                return false;
            });

            $("body").on("click", ".removeclass", function (e) {
                if (x > 1) {
                    $(this).prev().remove();
                    $(this).remove();
                    $("#borrowForm >input:last").remove();
                    x--;
                }
                return false;
            })
            $.ajax({
                url: "/return/userCheck",
                type: "POST",
                dataType: "json",
                data: $("#idcardForm").serialize(),
                success: function (data) {
                    switch (data.result) {
                        case "1":
                            $("#username1").html(data.username);
                            $("#userid").val(data.userid);
                            $("#userNameInfo").show();
                            $("#userNotFound").hide();
                            $("#notNeedToReturn").hide();
                            $("#bookDiv").show();
                            MaxInputs = data.maxReturnNum;
                            break;
                        case "2":
                            $("#userNameInfo").hide();
                            $("#userNotFound").show();
                            $("#notNeedToReturn").hide();
                            $("#bookDiv").hide();
                            break;
                        case "3":
                            $("#username2").html(data.username);
                            $("#userNameInfo").hide();
                            $("#userNotFound").hide();
                            $("#bookDiv").hide();
                            $("#notNeedToReturn").show();
                            break;
                    }
                },
                error: function () {
                    alert("网络出现问题！");
                }
            })
        });
        $("#bookSubmit").click(function () {
            $.ajax({
                url: "/return/bookCheck",
                type: "POST",
                dataType: "json",
                data: $("#bookForm").serialize(),
                success: function (data) {
                    if (data.result == "1") {
                        var booklist = data.booklist;
                        var length = booklist.length;
                        var html = "";
                        for (var i = 0; i < length; i++) {
                            if (i == length - 1)
                                html += "《" + booklist[i].bookname + "》";
                            else
                                html += "《" + booklist[i].bookname + "》、";
                        }
                        $("#bookNames").html(html);
                        $("#borrowForm input").each(function () {
                            if ($(this).index() > 0 && $(this).index() < length + 1) {
                                $(this).val(booklist[$(this).index() - 1].bookid);
                            }
                        });
                        $("#bookNotFound").hide();
                        $("#confirm").show();
                        $("#orderNotFound").hide();
                    }
                    else {
                        $("#bookNotFound").show();
                        var notFonudList = data.notFoundList;
                        var length = notFonudList.length;
                        var html = "";
                        for (var i = 0; i < length; i++) {
                            if (i == length - 1)
                                html += notFonudList[i];
                            else
                                html += notFonudList[i] + "、";
                        }
                        $("#bookNotFonudisbns").html(html);
                        $("#orderNotFound").hide();
                    }
                },
                error: function () {
                    alert("网络出现问题！");
                }
            })
        });

        $("#confirmButton").click(function () {
            $.ajax({
                url: "/return/returnBook",
                type: "POST",
                dataType: "json",
                data: $("#borrowForm").serialize(),
                success: function (data) {
                    if (data.result == "success") {
                        if (data.hasTicket == true) {
                            alert("还书成功，因超期还书本次产生罚款工" + data.fee + "元");
                            window.location.reload();
                        }
                        else {
                            alert("还书成功！本次还书没有罚款");
                            window.location.reload();
                        }
                        $("#confirm").hide();
                        $("#bookDiv").hide();
                        $("#userNameInfo").hide();
                        $("#idcard").val("");
                        $("#isbn").val("");
                    }
                    else {
                        var list = data.orderNotFoundList;
                        var html = "";
                        var length = list.length;
                        $("#orderNotFound").show();
                        $("#confirm").hide();
                        for (var i = 0; i < length; i++) {
                            if (i == length - 1)
                                html += $("#bookForm div input:eq(" + i + ")").val();
                            else
                                html = $("#bookForm div input:eq(" + i + ")").val() + "、";
                        }
                        $("#orderNotFoundList").html(html);
                    }
                },
                error: function () {
                    alert("网络出现问题！");
                }
            })
        });
    });
</script>
</body>


</html>
