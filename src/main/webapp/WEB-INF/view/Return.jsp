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
    <link href="https://cdn.datatables.net/select/1.2.6/css/select.dataTables.min.css" rel="stylesheet">
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
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
                    <a href="${pageContext.request.contextPath}/overDueReminder">
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
            <h3><i class="fa fa-angle-right"></i>还书</h3>
            <hr>
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
                        <div class="message-p pn" style="min-height: 700px">
                            <div class="message-header">
                                <h5>请选择您要归还的图书</h5>
                            </div>
                            <div class="row mt">
                                <div class="col-md-12">
                                    <div class="adv-table">
                                        <table cellpadding="0" cellspacing="0" border="0"
                                               class="display table table-bordered"
                                               id="adv-dataTable" width="100%">
                                            <thead>
                                            <tr>
                                                <th class="select-checkbox sorting_1"></th>
                                                <th><i class="fa fa-bookmark"></i>订单编号</th>
                                                <th><i class="fa fa-bullhorn"></i>用户名</th>
                                                <th><i class="fa fa-bookmark"></i>书名</th>
                                                <th><i class="fa fa-bookmark"></i>借书时间</th>
                                                <th><i class="fa fa-bookmark"></i>应还时间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-5"></div>
                                <div class="col-md-6">
                                    <button id="bookSubmit" type="button" class="btn btn-default">下一步</button>
                                </div>
                            </div>
                            <div id="confirm" class="row" style="display: none">
                                <div class="col-md-3 centered hidden-sm hidden-xs">
                                    <img src="background/img/prompt.png" class="img-circle" width="65">
                                </div>
                                <div class="col-md-9">
                                    <p>您要归还的图书名为：<b id="bookNames"></b></p>
                                    <button id="confirmButton" type="button" class="btn btn-default">确认还书</button>
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
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/dataTables.select.js"></script>
<script class="include" type="text/javascript" src="background/lib/jquery.dcjqaccordion.2.7.js"></script>
<script type="text/javascript" src="background/lib/advanced-datatable/js/DT_bootstrap.js"></script>
<!--script for this page-->
<script src="background/lib/form-validation-script.js"></script>
<script type="text/javascript">
    var oTable;
    var url;
    $(document).ready(function () {
        $("#idSubmit").click(function () {
            if ($("#idcard").val().trim() === "") {
                alert("未输入身份证");
                return;
            }

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
                            $("#confirm").hide();
                            $("#bookDiv").show();
                            url = "/borrowRec/getNotReturnList?userid=" + data.userid;
                            oTable.ajax.url(url).load();
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
        oTable = $("#adv-dataTable").DataTable({
            "dom": "<'row'<'col-sm-12'lBrtip>>",
            "columns": [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false,
                    searchable: false,
                    width: "5%"
                },
                {"data": "orderid"},
                {"data": "username"},
                {"data": "bookname"},
                {"data": "borrowtime"},
                {"data": "limittime"},
                {
                    "data": "bookid", visible: false
                },
                {
                    "data": "userid", visible: false
                }
            ],
            "paging": false,//开启表格分页
            "bLengthChange": true,
            "bRetrieve": true,
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": [0]
            }],
            select: {
                style: 'mutil',
                selector: 'td:first-child'
            },
            "order": [[1, 'asc']],
            "oLanguage": { // 国际化配置
                "sProcessing": "正在获取数据，请稍后...",
                "sLengthMenu": "显示 _MENU_ 条",
                "sZeroRecords": "没有找到数据",
                "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                "sInfoEmpty": "记录数为0",
                "sInfoFiltered": "(全部记录数 _MAX_ 条)",
                "sInfoPostFix": "",
                "sSearch": "搜索",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "最后一页"
                },

            },
            "autoWidth": true,
            "bAutoWidth": true,
        });
        oTable.columns.adjust().draw();

        $("#bookSubmit").click(function () {
            var Data = oTable.rows('.selected').data();
            var length = Data.length;
            if (length === 0) {
                $("#confirm").hide();
                alert("未选择任何图书");
                return;
            }
            var html = "";
            for (var i = 0; i < length; i++) {
                if (i === length - 1)
                    html += "《" + Data[i].bookname + "》";
                else
                    html += "《" + Data[i].bookname + "》、";
            }
            $("#bookNames").html(html);
            $("#confirm").show();
        });

        $("#confirmButton").click(function () {
            var rowData = oTable.rows('.selected').data();
            var data = new Array(rowData.length);
            for (var i = 0; i < rowData.length; i++) {
                data[i] = rowData[i];
            }
            $.ajax({
                url: "/return/returnBook",
                type: "POST",
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(data),
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

    function initTable() {

    }
</script>
</body>


</html>
