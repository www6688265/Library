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
    <link href="background/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="background/css/editor.bootstrap.min.css" rel="stylesheet">
    <!-- shop css -->
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
    <link href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/select/1.2.6/css/select.dataTables.min.css" rel="stylesheet">
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
                        <!-- Hidden li  included to remove active class from about link when scrolled up past about section -->
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
                </ul>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true" onclick="reset()">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form class="cmxform form-horizontal style-form" id="changPwdForm" method="post" action="">
                        <div class="form-group ">
                            <label for="username" class="control-label col-lg-2">当前密码</label>
                            <div class="col-lg-10">
                                <input type="password" id="pwd" name="pwd" class="form-control" placeholder="当前密码"
                                       required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="idcard" class="control-label col-lg-2">新密码</label>
                            <div class="col-lg-10">
                                <input type="password" id="newPwd" name="newPwd" class="form-control"
                                       placeholder="新密码" required minlength="8">
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="sex" class="control-label col-lg-2">确认密码</label>
                            <div class="col-lg-10">
                                <input type="password" id="agaPwd" name="agaPwd" class="form-control"
                                       placeholder="请再输入一次密码" required minlength="8">
                            </div>
                        </div>
                        <input style="display: none" class="form-control " id="userStatus" name="userStatus" type="text"
                               value="1"/>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button class="btn btn-theme" type="submit">修改密码</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--//breadcrumbs ends here-->
    <!-- signin and signup form -->
    <div class="about-sec section">
        <div class="col-lg-12">
            <div class="row content-panel">
                <div class="col-md-4 profile-text mt mb centered">
                    <div class="right-divider">
                        <h4 id="notReturnNum">一共借了</h4>
                        <br/>
                        <h4 id="overDueNum">过期了</h4>
                        <br/>
                        <h4 id="userFee">罚款</h4>
                    </div>
                </div>
                <!-- /col-md-4 -->
                <div class="col-md-4 profile-text">
                    <h3 id="username"></h3>
                    <p id="idcard">身份证号：</p>
                    <p id="sex">性别：</p>
                    <p id="access">权限：</p>
                    <p id="accessDetail">原因：</p>
                    <br>
                </div>
                <!-- /col-md-4 -->
                <div class="col-md-4 profile-text">
                    <button class="btn btn-success" data-toggle="modal" data-target="#myModal">更改密码</button>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /col-lg-12 -->
        <div class="col-lg-12 mt">
            <div class="row content-panel">
                <div class="panel-heading">
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active">
                            <a data-toggle="tab" href="#contact" class="contact-map">借书记录查询</a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#edit">罚单记录查询</a>
                        </li>
                    </ul>
                </div>
                <!-- /panel-heading -->
                <div class="panel-body">
                    <div class="tab-content">
                        <!-- /tab-pane -->
                        <div id="contact" class="tab-pane active ">
                            <div class="row mt">
                                <div class="col-md-12">
                                    <div class="adv-table">
                                        <table cellpadding="0" cellspacing="0" border="0"
                                               class="display table table-bordered"
                                               id="adv-dataTable" width="100%">
                                            <thead>
                                            <tr>
                                                <th><i class="fa fa-bullhorn"></i>用户名</th>
                                                <th><i class="fa fa-bookmark"></i>书名</th>
                                                <th><i class="fa fa-bookmark"></i>借书时间</th>
                                                <th><i class="fa fa-bookmark"></i>应还时间</th>
                                                <th><i class="fa fa-bookmark"></i>还书时间</th>
                                                <th><i class="fa fa-edit"></i>管理</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <!-- /row -->
                            </div>
                        </div>
                        <!-- /tab-pane -->
                        <div id="edit" class="tab-pane" style="width: 100%">
                            <div class="row mt">
                                <div class="col-md-12">
                                    <div class="adv-table">
                                        <table cellpadding="0" cellspacing="0" border="0"
                                               class="display table table-bordered"
                                               id="adv-dataTable1" style="width: 100%">
                                            <thead>
                                            <tr>
                                                <th><i class="fa fa-bullhorn"></i>用户名</th>
                                                <th><i class="fa fa-bookmark"></i>书名</th>
                                                <th><i class="fa fa-bookmark"></i>归还时间</th>
                                                <th><i class="fa fa-bookmark"></i>过期时长</th>
                                                <th><i class="fa fa-bookmark"></i>费用</th>
                                                <th><i class="fa fa-bookmark"></i>状态</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /row -->
                        </div>
                        <!-- /tab-pane -->
                    </div>
                    <!-- /tab-content -->
                </div>
                <!-- /panel-body -->
            </div>
            <!-- /col-lg-12 -->
        </div>
        <!-- /row -->
    </div>
</div>
<!--//signin and signup form ends here-->
<!-- //footer -->
<!-- //home -->
<!-- js -->
<script type="text/javascript" language="javascript" src=https://code.jquery.com/jquery-3.3.1.js></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<!-- //js -->
<!--search jQuery-->
<script src="front/js/main.js"></script>
<!--search jQuery-->

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

    });
</script>
<script src="front/js/SmoothScroll.min.js"></script>
<!-- //smooth-scrolling-of-move-up -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/jquery.dataTables.js"></script>
<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/dataTables.select.js"></script>
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/dataTables.buttons.js"></script>
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
<script src="background/lib/dataTables.editor.min.js"></script>
<script type="text/javascript" src="background/lib/advanced-datatable/js/DT_bootstrap.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
</body>
<script>
    var oTable;
    var userid = "";
    userid += "${userid}";
    $(document).ready(function () {
        $.ajax({
            url: "/user/getProfile?userid=" + userid,
            success: function (data) {
                $("#username").append(data.username);
                $("#idcard").append(data.idcard);
                $("#sex").append(data.sex);
                if (data.access === 1) {
                    $("#access").append("可以借书");
                    $("#accessDetail").html("");
                } else {
                    $("#access").append("不可借书");
                    $("#accessDetail").append(data.accessDetail);
                }
                if (data.notReturnNum === 0) {
                    $("#notReturnNum").html("目前没有借书")
                }
                else {
                    $("#notReturnNum").append(data.notReturnNum + "本书");
                }
                if (data.overDueNum === 0) {
                    $("#overDueNum").html("没有过期图书")
                }
                else {
                    $("#overDueNum").append(data.overDueNum + "本书");
                }
                if (data.userFee === 0) {
                    $("#userFee").html("恭喜你没有罚款，请继续保持！");
                }
                else {
                    $("#userFee").append(data.userFee + "元");
                }
            }
        })


        oTable = $("#adv-dataTable").DataTable({
            "dom": "<'row'<'col-sm-12'lrtip>>",
            "ajax": {
                "url": "/borrowRec/getBorrowRecByUserID?id=" + userid,
            },
            "columns": [
                {"data": "username", width: "10%"},
                {"data": "bookname"},
                {"data": "borrowtime"},
                {"data": "limittime"},
                {
                    "data": "returntime", render: function (data, type, row, meta) {
                        if (data != null && !data.toString() == "")
                            return data;
                        else
                            return "未归还"
                    }
                },
                {
                    "data": "orderid", render: function (data, type, row, mete) {
                        return check(mete);
                    }
                },
                {
                    "data": "bookid",
                    visible: false
                }
            ],
            "pagingType": "full_numbers",
            "paging": true,//开启表格分页
            "bLengthChange": true,
            "bRetrieve": true,
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": [0]
            }],
            searching: true,
            "order": [[4, 'dasc']],
            "autoWidth": true,
            "bFilter": true,
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

        });
        oTable.columns.adjust().draw();

        $('#adv-dataTable tfoot th').each(function () {
            if ($(this).index() == 1) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html('<input type="text" placeholder="搜索' + title + '" />');
            } else if ($(this).index() > 1 && $(this).index() < 5) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html('<input class="input-medium default-date-picker" size="16" type="text" value="" placeholder="搜索' + title + '" >');
            }

        });
        $('#adv-dataTable tfoot tr').appendTo('#adv-dataTable thead');

        $("#adv-dataTable label input").addClass("col-xs-12");

        oTable.columns().every(function () {
            var that = this;
            $('input', this.footer()).on('keyup change', function () {
                if (this.name === "pwd" || this.name === "newPwd" || this.name === "agaPwd") {
                    return;
                }
                if (that.search() !== this.value) {
                    that
                        .search(this.value)
                        .draw();
                }
            });
        });

        // 第二个表格
        var oTable1;

        oTable1 = $("#adv-dataTable1").DataTable({
            "dom": "<'row'<'col-sm-12'lrtip>>",
            "ajax": {
                "url": "/ticketRec/getTicket?userid=" + userid,
            },
            "columns": [
                {"data": "borrowExt.username"},
                {"data": "borrowExt.bookname"},
                {
                    "data": "borrowExt.returntime", render: function (data, type, row, meta) {
                        if (data != null && !data.toString() === "")
                            return data;
                        else
                            return "未归还"
                    }
                },
                {
                    "data": "overduetime", render: function (data, type, row, meta) {
                        return data + "天";
                    }
                },
                {
                    "data": "fee", render: function (data, type, row, meta) {
                        return data + "元";
                    }
                },
                {
                    "data": "ticketStatus", render: function (data, type, row, meta) {
                        switch (data) {
                            case 0:
                                return "<span class=\"label label-warning label-mini\">未处理</span>";
                                break;
                            case 1:
                                return "<span class=\"label label-success label-mini\">已处理</span> ";
                                break;
                        }
                    }
                }
            ],
            "pagingType": "full_numbers",
            "paging": true,//开启表格分页
            "bLengthChange": true,
            "bRetrieve": true,
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": [0]
            }],
            searching: true,
            "bFilter": true,
            "autoWidth": true,
            "order": [[5, 'dasc']],
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

        });
        oTable1.columns.adjust().draw();

        $('#adv-dataTable1 tfoot th').each(function () {
            if ($(this).index() > 0 && $(this).index() < 6 && $(this).index() != 2) {
                var title = $('#adv-dataTable1 thead th').eq($(this).index()).text();
                $(this).html('<input type="text" placeholder="搜索' + title + '" />');
            }
            if ($(this).index() === 2) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html('<input class="input-medium default-date-picker" size="16" type="text" value="" placeholder="搜索' + title + '" >');
            }

        });
        $('#adv-dataTable1 tfoot tr').appendTo('#adv-dataTable1 thead');

        $("#adv-dataTable1 label input").addClass("col-xs-12");

        oTable1.columns().every(function () {
            var that = this;
            $('input', this.footer()).on('keyup change', function () {
                if (that.search() !== this.value) {
                    that
                        .search(this.value)
                        .draw();
                }
            });
        });

        function check(mete) {
            var index = mete.row;
            var data = oTable.row(index).data();
            var limittime = data.limittime;
            var returntime = data.returntime;
            var orderid = data.orderid;
            var bookid = data.bookid;
            limittime = limittime.replace("-", "/");
            var now = new Date();
            var d = new Date(Date.parse(limittime));
            if (d > now && returntime === null)
                return "<button onclick=\"renew(" + orderid + "," + bookid + ")\" class=\"btn btn-success\">续借</button>";
            else
                return "<button class=\"btn btn-danger\" disabled=\"disabled\">续借</button>"
        }

        $('.default-date-picker').datepicker({
            format: 'yyyy-mm-dd',
            clearBtn: true,
            language: "zh-CN",
        });

        $("#changPwdForm").validate({
            submitHandler: function () {
                var agaPwd = $("#agaPwd").val();
                if (agaPwd.indexOf(" ") >= 0) {
                    alert("密码不能包含空格");
                    return false;
                }
                var newPwd = $("#newPwd").val();
                if (newPwd.indexOf(" ") >= 0) {
                    alert("密码不能包含空格");
                    return false;
                }
                var pwd = $("#pwd").val();
                if (pwd === newPwd) {
                    alert("新密码不能和旧密码相同！");
                    return;
                }
                if (agaPwd === newPwd) {
                    $.ajax({
                        url: "/user/changePwd",
                        type: "POST",
                        dataType: "json",
                        data: $("#changPwdForm").serialize(),
                        success: function (data) {
                            if (data.result == "success") {
                                alert("修改密码成功");
                                window.location.href = "${pageContext.request.contextPath}/UserLogin";
                            }
                            else {
                                alert(data.result);
                            }
                        },
                        error: function (data) {
                            alert(data.result);
                        }
                    })
                }
                else {
                    alert("两次输入密码不一致");
                }
            }
        });


    });

    function reset() {
        $("#changPwdForm")[0].reset();
    }

    function renew(orderid, bookid) {
        if (confirm("确定续借？")) {
            $.ajax({
                url: "/borrowRec/renew?orderid=" + orderid + "&bookid=" + bookid,
                async: true,
                type: "GET",
                dataType: "json",
                cache: false,    //不允许缓存
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result === "success") {
                        oTable.ajax.reload();
                        alert("续借成功");
                    }
                    else {
                        alert(data.msg);
                    }
                },
                error: function (data) {
                    alert("请求异常");
                }
            });
        }
    }

</script>

</html>