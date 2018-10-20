<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>图书管理系统</title>
    <meta charset="utf-8">

    <!-- Favicons -->
    <link href="background/img/favicon.png" rel="icon">
    <link href="background/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap core CSS -->
    <link href="background/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="background/lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="background/css/style.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="background/lib/bootstrap-fileupload/bootstrap-fileupload.css"/>
    <link href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css" rel="stylesheet">
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
                    <a href="${pageContext.request.contextPath}/Return">
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
                    <a class="active" href="${pageContext.request.contextPath}/Admin_changePwd">
                        <i class="fa fa-key"></i>
                        <span>修改密码</span>
                    </a>
                </li>
            </ul>
        </div>
    </aside>
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> 修改管理员密码</h3>
            <!-- BASIC FORM ELELEMNTS -->
            <div id="login-page">
                <div class="container">
                    <form class="form-login" method="post" id="changPwdForm">
                        <h2 class="form-login-heading">修改密码</h2>
                        <div class="login-wrap">
                            <input type="password" id="pwd" name="pwd" class="form-control" placeholder="当前密码"
                                   autofocus required>
                            <br>
                            <input type="password" id="newPwd" name="newPwd" class="form-control"
                                   placeholder="新密码" required minlength="8">
                            <br>
                            <input type="password" id="agaPwd" name="agaPwd" class="form-control"
                                   placeholder="请再输入一次密码" required minlength="8">
                            <br>
                            <button id="changePwd" class="btn btn-theme btn-block" type="submit">
                                <i class="fa fa-lock"></i> 修改密码
                            </button>
                            <br>
                        </div>
                    </form>
                </div>
                <!-- col-lg-12-->
            </div>
        </section>
    </section>

    <footer class="site-footer">
        <div class="text-center">
            <p>
                &copy; Copyrights All Rights Reserved
            </p>
        </div>
    </footer>

</section>
<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="background/lib/jquery.scrollTo.min.js"></script>
<script src="background/lib/jquery.nicescroll.js" type="text/javascript"></script>
<!--common script for all pages-->
<script src="background/lib/common-scripts.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="background/lib/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#changPwdForm").validate({
            submitHandler: function () {
                var agaPwd = $("#agaPwd").val();
                var newPwd = $("#newPwd").val();
                if (agaPwd === newPwd) {
                    $.ajax({
                        url: "/admin/changePwd",
                        type: "POST",
                        dataType: "json",
                        data: $("#changPwdForm").serialize(),
                        success: function (data) {
                            if (data.result == "success") {
                                alert("修改密码成功");
                                window.location.href = "${pageContext.request.contextPath}/login";
                            }
                            else {
                                alert(data.result);
                            }
                        },
                        error: function (data) {
                            alert(data.error);
                            window.location.href = "${pageContext.request.contextPath}/login";
                        }
                    })
                }
                else {
                    alert("两次输入密码不一致");
                }
            }
        });
    });
</script>
</body>

</html>