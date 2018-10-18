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
                    <a  href="${pageContext.request.contextPath}/User_table">
                        <i class="fa fa-group"></i>
                        <span>用户管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a class="active" href="javascript:;">
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
                    <a  href="${pageContext.request.contextPath}/Borrow">
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
                        <h4><i class="fa fa-angle-right"></i>图书管理</h4>
                        <hr>
                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"
                               id="adv-dataTable">
                            <thead>
                            <tr>
                                <th class="select-checkbox sorting_1"><input type="checkbox" name="select_all"
                                                                             id="select-all"></th>
                                <th><i class="fa fa-bullhorn"></i>书名</th>
                                <th><i class="fa fa-bookmark"></i>作者</th>
                                <th><i class="fa fa-bookmark"></i>ISBN</th>
                                <th><i class="fa fa-bookmark"></i>库存</th>
                                <th><i class="fa fa-bookmark"></i>剩余数量</th>
                                <th><i class="fa fa-bookmark"></i>图书封面</th>
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
    var editor;
    var sOut;
    $(document).ready(function () {
        editor = new $.fn.dataTable.Editor({
            display: 'envelope',
            ajax: {
                url: "/book/updateBook",
                type: "POST",
                data: function (data) {
                    var result = {};
                    for (var i in data.data) {
                        var result = data.data[i];
                        result.bookid = i;
                        result.action = data.action;
                        console.log(result);
                    }
                    return result;
                },
            },
            table: "#adv-dataTable",
            fields: [{
                label: "书名：",
                name: "bookname"
            }, {
                label: "类型：",
                name: "type"
            }, {
                label: "出版社：",
                name: "press"
            }, {
                label: "作者：",
                name: "author"
            }, {
                label: "ISBN：",
                name: "isbn"
            }, {
                label: "简介：",
                name: "brief"
            }, {
                label: "库存：",
                name: "total"
            }, {
                label: "剩余数量：",
                name: "left"
            }, {
                label: "楼层：",
                name: "floor"
            }, {
                label: "书架：",
                name: "bookcase"
            }, {
                label: "层数：",
                name: "level"
            }, {
                label: "图书封面（不能超过1M）:",
                name: "pic",
                type: "upload",
                display: function (data) {
                    return '<img src="' + data + '"/>';
                },
                clearText: "删除",
                noImageText: '暂无图片'
            }
            ],
            idSrc: 'bookid',
            i18n: {
                edit: {
                    title: "修改数据",
                    submit: "更新数据"
                }
            }
        });


        oTable = $("#adv-dataTable").DataTable({
            "dom": "<'row'<'col-sm-12'lBrtip>>",
            buttons: [
                {
                    text: '刷新表格', className: 'red',
                    action: function (e, dt, node, config) {
                        dt.ajax.reload();
                    }
                },
                {
                    extend: 'edit', editor: editor, text: "编辑", className: 'btn btn-round btn-primary'
                }
            ],
            "ajax": {
                "url": "/book/getAllBooks",
            },
            "columns": [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false,
                    "width": "5px"
                },
                {"data": "bookname"},
                {
                    "data": "author", render: function (data) {
                        if (data == null || data.toString() == "")
                            return "暂未输入";
                        else return data;
                    }
                },
                {"data": "isbn"},
                {"data": "total", "width": "10%"},
                {"data": "left", "width": "10%"},
                {
                    "data": "pic", render: function (data) {
                        if (data != null && data.toString() != "")
                            return "<img src=" + data + " width=100%/>";
                        else
                            return null;
                    },
                    defaultContent: "没有封面图片",
                    title: "封面",
                    "width": "10%"
                }
                ,
                {
                    "data": "bookid", render: function (data, type, row, meta) {

                        var html = "<i><button class=\"btn btn-success btn-xs\" onclick=\"showDetail(this)\"><i class=\"fa fa-exclamation-circle\"></i></button></i>";
                        html += "<button class=\"btn btn-danger btn-xs\" onclick=\"bookDelete(this)\"><i class=\"fa fa-trash-o \"></i></button>"
                        return html;
                    }
                }
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
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
            if ($(this).index() > 0 && $(this).index() < 4) {
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


        $('#select-all').on('click', function () {
            if (this.checked) {
                oTable.rows().select();
            }
            else {
                oTable.rows().deselect();
            }
        });


    });

    function fnFormatDetails(aData) {
        sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
        $.ajax({
            url: "/book/getBook?id=" + aData.bookid,
            type: "GET",
            async: false,
            dataType: "json",
            success: function (data) {
                sOut += '<tr><td>出版社:</td><td>' + aData.press + '</td></tr>';
                sOut += '<tr><td>类型:</td><td>'+aData.type +'</td></tr>';
                sOut += '<tr><td>位置:</td><td>'+aData.floor+'楼，第'+aData.bookcase+'书架，第'+aData.level+'层'+'</td></tr>';
                return sOut;
            }
        });
    }

    function showDetail(td) {
        var nTr = $(td).parents('tr');
        var row = oTable.row(nTr);
        if (row.child.isShown()) {
            /* This row is already open - close it */
            row.child.hide();
        } else {
            /* Open this row */
            $.ajaxSettings.async = false;
            fnFormatDetails(row.data());
            row.child(sOut).show();
        }
    }


    function bookDelete(td) {
        var nTr = $(td).parents('tr');
        var row = oTable.row(nTr);
        var id=row.data().bookid;
        var pic=row.data().pic;
        if (confirm("确定要删除这条记录吗?")) {
            $.ajax({
                url: "/book/delBook?id=" + id+"&pic="+pic,
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
