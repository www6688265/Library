<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
    <link rel="stylesheet" type="text/css" href="background/lib/bootstrap-timepicker/compiled/timepicker.css"/>
    <link href="https://cdn.bootcss.com/bootstrap-switch/3.3.4/css/bootstrap2/bootstrap-switch.min.css"
          rel="stylesheet">
    <link href="background/css/style-responsive.css" rel="stylesheet">
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>

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
                    <a class="active" href="${pageContext.request.contextPath}/overDueReminder">
                        <i class="fa fa-clock-o"></i>
                        <span>超期提醒设置</span>
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
            <h3><i class="fa fa-angle-right"></i>超期提醒设置</h3>
            <hr>
            <!-- row -->

            <div class="row mt">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <form class="form-horizontal  style-form" action="#">
                            <div class="form-group">
                                <label class="control-label col-md-3">超期提醒</label>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <input class="form-control" type="checkbox" checked="" name="status">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" id="timeSelect">
                                <label class="control-label col-md-3">设置提示时间</label>
                                <div class="col-md-4">
                                    <div class="input-group bootstrap-timepicker">
                                        <input type="text" class="form-control timepicker-default" readonly="readonly">
                                        <span class="input-group-btn">
                                            <button class="btn btn-theme04" type="button"><i class="fa fa-clock-o"></i></button>
                                        </span>
                                    </div>
                                    <span class="help-block">该时间为系统每天检查是否有用户超期或者即将超期的时间，默认即将超期3天或当天已经刚好超期就提醒。</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-10">
                                    <button id="save" class="btn btn-theme" type="button">确定更改</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /row -->
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
<script src="https://cdn.bootcss.com/bootstrap-switch/3.3.4/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="background/lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $('.timepicker-default').timepicker({
            autoclose: true,
            minuteStep: 1,
            showSeconds: false,
            format: "hh:mm",
            showMeridian: false
        });
        var switchBtn = $('[name="status"]');
        switchBtn.bootstrapSwitch({
            onText: "开启",
            offText: "关闭",
            onColor: "success",
            offColor: "info",
            size: "big",
            onSwitchChange: function (event, state) {
                if (state === true) {
                    $("#timeSelect").show();
                    var time = new Date();
                    $('.timepicker-default').val(time.getHours() + ":" + (time.getMinutes() + 1));
                } else {
                    $('.timepicker-default').val("");
                    $("#timeSelect").hide();
                }
            }
        });
        $.getJSON("/reminder/getCurrentState", function (data) {
            if (data.state === "0") {
                switchBtn.bootstrapSwitch('state', false);
                $('.timepicker-default').val("");
            }
            else {
                $('.timepicker-default').val(data.time);
            }
        });
        $("#save").on("click", function () {
            $.post({
                url: "/reminder/startReminder",
                data: {
                    date: $('.timepicker-default').val()
                },
                success: function (data) {
                    alert(data.msg);
                    window.location.reload();
                }
            });
        })


    });

</script>

</body>
</html>
