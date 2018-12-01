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
    <link href="background/css/editor.bootstrap.min.css" rel="stylesheet">
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
                    <a class="active" href="${pageContext.request.contextPath}/Ticket_table">
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
        <section class="wrapper">
            <!-- row -->
            <div class="row mt">
                <div class="col-md-12">
                    <div class="adv-table">
                        <h4><i class="fa fa-angle-right"></i>罚款记录管理</h4>
                        <hr>
                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"
                               id="adv-dataTable">
                            <thead>
                            <tr>
                                <th><i class="fa fa-bullhorn"></i>罚款编号</th>
                                <th><i class="fa fa-bullhorn"></i>用户名</th>
                                <th><i class="fa fa-bookmark"></i>书名</th>
                                <th><i class="fa fa-bookmark"></i>归还时间</th>
                                <th><i class="fa fa-bookmark"></i>处理时间</th>
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
        src="background/lib/advanced-datatable/js/jquery.dataTables.js"></script>
<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/dataTables.select.js"></script>
<script type="text/javascript" language="javascript"
        src="background/lib/advanced-datatable/js/dataTables.buttons.js"></script>
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
<script class="include" type="text/javascript" src="background/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="background/lib/jquery.scrollTo.min.js"></script>
<script src="background/lib/dataTables.editor.min.js"></script>
<script src="background/lib/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="background/lib/advanced-datatable/js/DT_bootstrap.js"></script>
<script src="background/lib/common-scripts.js"></script>
<script src="background/lib/form-validation-script.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript">
    var oTable;
    var sOut;
    $(document).ready(function () {

        oTable = $("#adv-dataTable").DataTable({
            "dom": "<'row'<'col-sm-12'lBrtip>>",
            buttons: [
                {
                    text: '刷新表格', className: 'red',
                    action: function (e, dt, node, config) {
                        dt.ajax.reload();
                    }
                }
            ],
            "ajax": {
                "url": "/ticketRec/getAllTicketRec",
            },
            "columns": [
                {"data": "ticketid"},
                {"data": "borrowExt.username"},
                {"data": "borrowExt.bookname"},
                {
                    "data": "borrowExt.returntime", render: function (data, type, row, meta) {
                        if (data != null && !data.toString() == "")
                            return data;
                        else
                            return "未归还"
                    }
                },
                {
                    "data": "dealtime", render: function (data, type, row, meta) {
                        if (data != null && !data.toString() == "")
                            return data;
                        else
                            return "未处理"
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
            // "pagingType": "full_numbers",
            "paging": true,//开启表格分页
            "bLengthChange": true,
            "bRetrieve": true,
            searching: true,
            "bFilter": true,
            "order": [[0, 'asc']],
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
            "autoWidth": true
        });
        oTable.columns.adjust().draw();

        $('#adv-dataTable tfoot th').each(function () {
            if ($(this).index() < 5 || $(this).index() == 7) {
                if ($(this).index() === 4 || $(this).index() === 3) {
                    var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                    $(this).html('<input class="input-medium default-date-picker" size="16" type="text" value="" placeholder="搜索' + title + '" >');
                }
                else if ($(this).index() === 7) {
                    var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                    $(this).html(`<select>
                                    <option value="">全部状态</option>
                                    <option value="未处理">未处理</option>
                                    <option value="已处理">已处理</option>
                              </select>`);
                }
                else {
                    var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                    $(this).html('<input type="text" placeholder="搜索' + title + '" />');
                }
            }

        });
        $('#adv-dataTable tfoot tr').appendTo('#adv-dataTable thead');

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
        $('.default-date-picker').datepicker({
            format: 'yyyy-mm-dd',
            clearBtn: true,
            language: "zh-CN",
        });


    });


    // function recordDelete(td) {
    //     var nTr = $(td).parents('tr');
    //     var row = oTable.row(nTr);
    //     var id = row.data().ticketid;
    //     if (confirm("确定要删除这条记录吗?")) {
    //         $.ajax({
    //             url: "/ticketRec/delTicketRec?id=" + id,
    //             async: true,
    //             type: "GET",
    //             dataType: "json",
    //             cache: false,    //不允许缓存
    //             success: function (data) {
    //                 var obj = eval(data);
    //                 if (obj.result == "success") {
    //                     oTable.ajax.reload();
    //                     alert("删除成功");
    //                 }
    //                 else {
    //                     alert(data.msg);
    //                 }
    //             },
    //             error: function (data) {
    //                 alert("请求异常");
    //             }
    //         });
    //     }
    // }

</script>

</body>
</html>
