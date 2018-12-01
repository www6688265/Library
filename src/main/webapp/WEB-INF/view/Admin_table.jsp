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
    <link href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" rel="stylesheet">
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
                    <a href="${pageContext.request.contextPath}/Return">
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
                    <a class="active" href="${pageContext.request.contextPath}/Admin_table">
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
        <section class="wrapper">
            <!-- row -->
            <div class="row mt">
                <div class="col-md-12">
                    <div class="adv-table">
                        <h4><i class="fa fa-angle-right"></i>管理员管理</h4>
                        <hr>
                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"
                               id="adv-dataTable">
                            <button class="btn btn-round btn-primary" id="addUser" data-toggle="modal"
                                    data-target="#myModal" onclick="">增加管理员
                            </button>

                            <thead>
                            <tr>
                                <th><i class="fa fa-bullhorn"></i>用户名</th>
                                <th><i class="fa fa-bookmark"></i>权限</th>
                                <th><i class=" fa fa-edit"></i>管理</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
                <!-- /content-panel -->
            </div>

            <!-- /row -->

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增</h4>
                        </div>
                        <div class="modal-body">
                            <form class="cmxform form-horizontal style-form" id="adminForm" method="get" action="">
                                <div class="form-group ">
                                    <label for="idcard" class="control-label col-lg-2">用户名</label>
                                    <div class="col-lg-10">
                                        <input class=" form-control" id="idcard" name="idcard" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="admpassword" class="control-label col-lg-2">密码</label>
                                    <div class="col-lg-10">
                                        <input class=" form-control" id="admpassword" name="admpassword" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="admright" class="control-label col-lg-2">权限</label>
                                    <div class="col-lg-10">
                                        <select class="form-control" id="admright" name="admright">
                                            <option value="0" selected>图书管理员</option>
                                            <option value="1">系统管理员</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button class="btn btn-theme" type="submit">保存</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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

<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/buttons/1.5.4/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
<script class="include" type="text/javascript" src="background/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="background/lib/jquery.scrollTo.min.js"></script>
<script src="background/lib/dataTables.editor.min.js"></script>
<script src="background/lib/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="background/lib/advanced-datatable/js/DT_bootstrap.js"></script>
<script src="background/lib/common-scripts.js"></script>
<!--script for this page-->
<script src="background/lib/form-validation-script.js"></script>
<script type="text/javascript">
    var oTable;
    var editor;
    $(document).ready(function () {
        editor = new $.fn.dataTable.Editor({
            ajax: {
                url: "/admin/updateAdmin",
                type: "POST",
                data: function (data) {
                    var result = {};
                    for (var i in data.data) {
                        var result = data.data[i];
                        result.admid = i;
                        result.action = data.action;
                        console.log(result);
                    }
                    return result;
                },
            },
            table: "#adv-dataTable",
            fields: [{
                label: "",
                name: "idcard"
            }, {
                label: "",
                name: "admright",
                type: "select"
            }, {
                label: "",
                name: "admid"
            }
            ],
            idSrc: 'admid',
        });
        var accessOptions = [{label: "系统管理员", value: "1"}, {label: "图书管理员", value: "0"}];
        editor.field('admright').update(accessOptions);

        //修改用户信息方法
        $('#adv-dataTable').on('click', 'tbody td:not(:nth-child(3)):not(:first-child)', function (e) {
            editor.inline(this, {
                buttons: {
                    label: '提交', fn: function () {
                        this.submit();
                    }
                }
            });
        });

        oTable = $("#adv-dataTable").DataTable({
            "ajax": {
                "url": "${pageContext.request.contextPath}/admin/getAllAdmins",
            },
            "columns": [
                {"data": "idcard"},
                {
                    "data": "admright", render: function (data, type, row, meta) {
                        switch (data) {
                            case 0:
                                return "<span class=\"label label-warning label-mini\">图书管理员</span>";
                                break;
                            case 1:
                                return "<span class=\"label label-success label-mini\">系统管理员</span> ";
                                break;
                        }
                    }
                }
                ,
                {
                    "data": "admid", render: function (data, type, row, meta) {
                        var html = "<button class=\"btn btn-danger btn-xs\" onclick=\"userDelete(" + data + ")\"><i class=\"fa fa-trash-o \"></i></button>"
                        return html;
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
                "order": [[1, 'asc']]
            },

        });

        $('#adv-dataTable tfoot th').each(function () {
            if ($(this).index() === 0) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html('<input type="text" placeholder="搜索' + title + '" />');
            }
            if ($(this).index() === 1) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html(`<select>
                                    <option value="">全部权限</option>
                                    <option value="系统管理员">系统管理员</option>
                                    <option value="图书管理员">图书管理员</option>
                              </select>`);
            }

        });
        $('#adv-dataTable tfoot tr').appendTo('#adv-dataTable thead');

        $("#adv-dataTable label input").addClass("col-xs-12");

        oTable.columns().every(function () {
            var that = this;
            $('input', this.footer()).on('keyup change', function () {
                if (that.search() !== this.value) {
                    that
                        .search(this.value)
                        .draw();
                }
            });
            $('select', this.footer()).on('change', function () {
                if (that.search() !== this.value) {
                    that
                        .search(this.value)
                        .draw();
                }
            });
        });


    });


    function userDelete(id) {
        if (confirm("确定要删除这条记录吗?")) {
            $.ajax({
                url: "/admin/delAdmin?id=" + id,
                async: true,
                type: "GET",
                dataType: "json",
                cache: false,    //不允许缓存
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result == "success") {
                        alert("删除成功");
                        oTable.ajax.reload();
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
</body>

</html>
