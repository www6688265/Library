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
                        <h4><i class="fa fa-angle-right"></i>图书管理</h4>
                        <hr>
                        <div class="form-panel">
                            <h4 class="mb"><i class="fa fa-angle-right"></i>条件查询</h4>
                            <form class="form-horizontal style-form" role="form" id="findBookForm">
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <input type="text" name="bookname" id="bookname" class="form-control"
                                               placeholder="书名">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" name="author" id="author" class="form-control"
                                               placeholder="作者">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="booktypeid" name="booktypeid" class="form-control">
                                            <option value="" selected>-------选择图书类型-------</option>

                                        </select>
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" name="press" id="press" class="form-control"
                                               placeholder="出版社">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                        <input type="text" name="isbn" id="isbn" class="form-control"
                                               placeholder="ISBN">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" name="total" id="total" class="form-control"
                                               placeholder="库存">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" name="left_num" id="left_num" class="form-control"
                                               placeholder="剩余数量">
                                        <label class="col-sm-2 col-sm-2 control-label"></label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="display" name="display" class="form-control">
                                            <option value="" selected>---------上架状态----------</option>
                                            <option value="1">已上架</option>
                                            <option value="0">未上架</option>
                                        </select>
                                    </div>
                                </div>
                                <button id="search" type="button" class="btn btn-theme">搜索</button>
                            </form>
                        </div>
                        <!-- /form-panel -->
                        <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered"
                               id="adv-dataTable">
                            <thead>
                            <tr>
                                <th class="select-checkbox sorting_1"></th>
                                <th><i class="fa fa-bullhorn"></i>书名</th>
                                <th><i class="fa fa-bookmark"></i>作者</th>
                                <th><i class="fa fa-bookmark"></i>类型</th>
                                <th><i class="fa fa-bookmark"></i>ISBN</th>
                                <th><i class="fa fa-bookmark"></i>库存</th>
                                <th><i class="fa fa-bookmark"></i>剩余数量</th>
                                <th><i class="fa fa-bookmark"></i>上架情况</th>
                                <th><i class="fa fa-bookmark"></i>图书封面</th>
                                <th><i class="fa fa-edit"></i>管理</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
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
<script type="text/javascript">
    var oTable;
    var editor;
    var sOut;
    var optionsA = [];
    var optionsB = [];
    $(document).ready(function () {
        $.ajax({
            url: "/book/getAllTypes",
            success: function (data) {
                for (var type of data)
                    $("#booktypeid").append(`<option value=` + type.id + `>` + type.booktype + `</option>`)
            }
        });
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
                name: "booktypeid",
                type: "select"
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
                name: "left_num"
            }, {
                label: "楼层：",
                name: "floor",
                type: "select"
            }, {
                label: "书架：",
                name: "bookcase",
                type: "select"
            }, {
                label: "层数：",
                name: "level",
                type: "select"
            }, {
                label: "图书封面（不能超过1M,类型位jpg,png,gif）:",
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

        $.getJSON("book/getAllTypes", {
                term: "-1"
            },
            function (data) {
                var option = {};
                console.log(optionsB)
                $.each(data, function (i, e) {
                    option.label = e.booktype;
                    option.value = e.id;
                    optionsB.push(option);
                    option = {};
                });
            }
        ).done(function () {
            editor.field('booktypeid').update(optionsB);
        });

        var floorOptions = [];
        var bookcaseOptions = [];
        var levelOptions = [];
        var option = {};
        for (var i = 1; i < 11; i++) {
            option.label = i;
            option.value = i;
            floorOptions.push(option);
            bookcaseOptions.push(option);
            levelOptions.push(option);
            option = {};
        }
        editor.field('floor').update(floorOptions);
        editor.field('bookcase').update(bookcaseOptions);
        editor.field('level').update(levelOptions);

        $.fn.dataTable.ext.errMode = function (s, h, m) {
            if (h == 1) {
                alert("连接服务器失败！");
            } else if (h == 7) {
                alert("返回数据错误！");
            }
        };

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
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "/book/getAllBooks",
                "data": function (d) {
                    var option = {};
                    $.each(d, function (i, e) {
                        option.label = e.type;
                        option.value = e.id;
                        optionsA.push(option);
                        option = {};
                    });

                    var json = JSON.stringify(d);
                    return json;
                },
                type: "POST",
                contentType: "application/json;charset=UTF-8",
            },
            "columns": [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false,
                    searchable: false,
                    width: "3%"
                },
                {
                    "data": "bookname",
                    width: "10%"
                },
                {
                    "data": "author", render: function (data) {
                        if (data == null || data.toString() === "")
                            return "暂未输入";
                        else return data;
                    },
                    width: "10%"
                },
                {
                    "data": "booktype",
                    width: "10%"
                },
                {
                    "data": "isbn",
                    width: "10%"
                },
                {
                    "data": "total",
                    width: "10%"
                },
                {
                    "data": "left_num",
                    width: "10%"
                },
                {
                    "data": "display",
                    render: function (data) {
                        if (data === 0)
                            return "未上架";
                        else return "已上架";
                    },
                    width: "10%"
                },
                {
                    "data": "pic", render: function (data) {
                        if (data != null && data.toString() !== "")
                            return "<img src=" + data + " width=\"50%\"/>";
                        else
                            return null;
                    },
                    defaultContent: "没有封面图片",
                }
                ,
                {
                    "data": "bookid",
                    "width": "10%", render: function (data, type, row, meta) {
                        var html = "<i><button class=\"btn btn-info btn-xs\" onclick=\"showDetail(this)\">更多信息</button></i>";
                        if (oTable.row(meta.row).data().display === 1)
                            html += "<i><button class=\"btn btn-danger btn-xs\" onclick=\"hideBook(" + data + ")\">图书下架</button></i>";
                        else
                            html += "<i><button class=\"btn btn-success btn-xs\" onclick=\"showBook(" + data + ")\">图书上架</button></i>";
                        return html;
                    }
                }, {
                    "data": "press", visible: false
                }
                , {
                    "data": "floor", visible: false
                }
                , {
                    "data": "bookcase", visible: false
                }
                , {
                    "data": "level", visible: false
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
            searching: true,
            "bFilter": true,
            "order": [[1, 'asc']],
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": [0]
            }],
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
            "autoWidth": false
        });
        oTable.columns.adjust().draw();

        $(document).on("click", "#search", function () {
            var bookname = $("#bookname").val();
            bookname = bookname ? bookname : null;
            var author = $("#author").val();
            author = author ? author : null;
            var booktypeid = $("#booktypeid").val();
            booktypeid = booktypeid ? booktypeid : null;
            var press = $("#press").val();
            press = press ? press : null;
            var isbn = $("#isbn").val();
            isbn = isbn ? isbn : null;
            var total = $("#total").val();
            total = total ? total : null;
            var left_num = $("#left_num ").val();
            left_num = left_num ? left_num : null;
            var display = $("#display").val();
            display = display ? display : null;
            var result = {bookname, author, booktypeid, press, isbn, total, left_num, display};
            oTable.search(JSON.stringify(result)).draw();

        });
    });

    function fnFormatDetails(aData) {
        sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
        sOut += '<tr><td>出版社:</td><td>' + aData.press + '</td></tr>';
        sOut += '<tr><td>位置:</td><td>' + aData.floor + '楼，第' + aData.bookcase + '书架，第' + aData.layer + '层' + '</td></tr>';
        return sOut;
    }

    function showDetail(td, data) {
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

    function showBook(data) {
        var bookid = data;
        if (confirm("确定要上架此书吗?")) {
            $.ajax({
                url: "/book/showBook",
                async: true,
                type: "POST",
                dataType: "json",
                data: {
                    id: bookid,
                    display: true,
                },
                cache: false,    //不允许缓存
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result == "success") {
                        oTable.ajax.reload();
                        alert("上架成功");
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

    function hideBook(data) {
        var bookid = data;
        if (confirm("确定要下架此书吗?")) {
            $.ajax({
                url: "/book/showBook",
                async: true,
                type: "POST",
                dataType: "json",
                data: {
                    id: bookid,
                    display: false,
                },
                cache: false,    //不允许缓存
                success: function (data) {
                    var obj = eval(data);
                    if (obj.result == "success") {
                        oTable.ajax.reload();
                        alert("下架成功");
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
