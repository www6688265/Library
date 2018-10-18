<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>图书管理系统</title>
    <meta charset="utf-8">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-fileupload/bootstrap-fileupload.css"/>
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
                <li><a class="logout" href="${pageContext.request.contextPath}/login.jsp">退出登录</a></li>
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
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> 新书上架</h3>
            <!-- BASIC FORM ELELEMNTS -->
            <div class="row mt">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <form id="addBookForm" class="form-horizontal style-form" method="post"
                              action="${pageContext.request.contextPath}/book/addBook" enctype ="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">书名</label>
                                <div class="col-sm-10">
                                    <input type="text" id="bookname" name="bookname" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">类型</label>
                                <div class="col-sm-10">
                                    <input type="text" id="type" name="type" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">出版社</label>
                                <div class="col-sm-10">
                                    <input type="text" id="press" name="press" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">作者</label>
                                <div class="col-sm-10">
                                    <input type="text" id="author" name="author" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">ISBN</label>
                                <div class="col-sm-10">
                                    <input type="text" id="isbn" name="isbn" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">库存</label>
                                <div class="col-sm-10">
                                    <input type="text" id="total" name="total" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">楼层</label>
                                <div class="col-sm-10">
                                    <input type="text" id="floor" name="floor" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">书架</label>
                                <div class="col-sm-10">
                                    <input type="text" id="bookcase" name="bookcase" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">层数</label>
                                <div class="col-sm-10">
                                    <input type="text" id="level" name="level" class="form-control">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="brief" class="control-label col-lg-2">简介</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control " id="brief" name="brief"></textarea>
                                </div>
                            </div>
                            <div class="form-group last">
                                <label class="control-label col-md-3">图书封面上传</label>
                                <div class="col-md-9">
                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                            <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
                                                 alt=""/>
                                        </div>
                                        <div class="fileupload-preview fileupload-exists thumbnail"
                                             style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                        <div>
                                            <span class="btn btn-theme02 btn-file">
                                                <span class="fileupload-new"><i class="fa fa-paperclip"></i> 选择图片</span>
                                            <span class="fileupload-exists"><i class="fa fa-undo"></i> 更改</span>
                                            <input name="file" type="file" class="default"/>
                                            </span><a class="btn btn-theme04 fileupload-exists"
                                                                   data-dismiss="fileupload"><i class="fa fa-trash-o"></i> 删除</a>
                                        </div>
                                    </div>
                                        <span class="label label-info">注意！</span>
                                        <span>只允许大小为3M一下的文件</span>
                                    </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button id="save" class="btn btn-theme" type="button">保存</button>
                                    <button class="btn btn-theme04" type="button">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
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
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="lib/jquery.scrollTo.min.js"></script>
<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
<!--common script for all pages-->
<script src="lib/common-scripts.js"></script>
<!--script for this page-->
<script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
<!--custom switch-->
<script src="lib/bootstrap-switch.js"></script>
<!--custom tagsinput-->
<script src="lib/jquery.tagsinput.js"></script>
<!--custom checkbox & radio-->
<script type="text/javascript" src="lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="lib/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="lib/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="lib/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
<script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="lib/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script src="lib/jquery.form.js"></script>
<script src="lib/advanced-form-components.js"></script>
<script src="lib/form-validation-script.js"></script>
<script type="text/javascript">
    jQuery.browser = {};
    (function () {
        jQuery.browser.msie = false;
        jQuery.browser.version = 0;
        if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
            jQuery.browser.msie = true;
            jQuery.browser.version = RegExp.$1;
        }
    })();
    $(document).ready(function() {
        $("#save").click(function () {

            $("#addBookForm").ajaxSubmit({
                url:"/book/addBook",
                success:function (data) {
                    if(data.result=="success"){
                        alert("添加图书成功");
                        window.open('${pageContext.request.contextPath}/Book_table','_self')
                    }
                    else{
                        alert(data.msg);
                    }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            });

        });
    });
</script>
</body>

</html>