<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>借书记录管理</title>

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">
    <link href="css/editor.bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/select/1.2.6/css/select.dataTables.min.css" rel="stylesheet">
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
                    <a href="${pageContext.request.contextPath}/User_table">
                        <i class="fa fa-dashboard"></i>
                        <span>用户管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a  href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>图书管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="${pageContext.request.contextPath}/addBook">新书上架</a></li>
                        <li><a href="${pageContext.request.contextPath}/Book_table">图书管理</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a class="active" href="${pageContext.request.contextPath}/Borrow_table">
                        <i class="fa fa-cogs"></i>
                        <span>借书记录管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/Ticket_table">
                        <i class="fa fa-book"></i>
                        <span>罚单管理</span>
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
                        <h4><i class="fa fa-angle-right"></i>借书记录管理</h4>
                        <hr>
                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"
                               id="adv-dataTable">
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
        src="lib/advanced-datatable/js/jquery.dataTables.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
        src="lib/advanced-datatable/js/dataTables.select.js"></script>
<script type="text/javascript" language="javascript"
        src="lib/advanced-datatable/js/dataTables.buttons.js"></script>
<script type="text/javascript" language="javascript"
        src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="lib/jquery.scrollTo.min.js"></script>
<script src="lib/dataTables.editor.min.js"></script>
<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="lib/advanced-datatable/js/DT_bootstrap.js"></script>
<script src="lib/common-scripts.js"></script>
<script src="lib/form-validation-script.js"></script>
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
                "url": "/borrowRec/getAllBorrowRec",
            },
            "columns": [
                {"data": "username"},
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
                    "data": "orderid", render: function (data, type, row, meta) {

                        var html = "<button class=\"btn btn-danger btn-xs\" onclick=\"recordDelete(this)\"><i class=\"fa fa-trash-o \"></i></button>"
                        return html;
                    }
                }
            ],
            // "pagingType": "full_numbers",
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
            "autoWidth": true
        });
        oTable.columns.adjust().draw();

        $('#adv-dataTable tfoot th').each(function () {
            if ($(this).index() < 5) {
                var title = $('#adv-dataTable thead th').eq($(this).index()).text();
                $(this).html('<input type="text" placeholder="搜索' + title + '" />');
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
        });


    });


    function recordDelete(td) {
        var nTr = $(td).parents('tr');
        var row = oTable.row(nTr);
        var id = row.data().orderid;
        if (confirm("确定要删除这条记录吗?")) {
            $.ajax({
                url: "/borrowRec/delBorrowRec?id=" + id ,
                async: true,
                type: "GET",
                dataType: "json",
                cache: false,    //不允许缓存
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result == "success") {
                        oTable.ajax.reload();
                        alert("删除成功");
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
