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
    <link rel="stylesheet" type="text/css" href="background/lib/bootstrap-fileupload/bootstrap-fileupload.css"/>
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
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> 新书上架</h3>
            <!-- BASIC FORM ELELEMNTS -->
            <div class="row mt">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <form id="addBookForm" class="form-horizontal style-form" method="post"
                              action="${pageContext.request.contextPath}/book/addBook" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">ISBN</label>
                                <div class="col-sm-3">
                                    <input type="text" id="isbn" name="isbn" class="form-control">
                                </div>
                                <div class="col-sm-1">
                                    <b class="help-block">*必填</b>
                                </div>
                                <button type="button" class="btn btn-round btn-primary" id="autoFill">自动填写
                                </button>

                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">书名</label>
                                <div class="col-sm-3">
                                    <input type="text" id="bookname" name="bookname" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">类型</label>
                                <div class="col-sm-3">
                                    <select id="booktypeid" name="booktypeid" class="form-control">

                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">出版社</label>
                                <div class="col-sm-3">
                                    <input type="text" id="press" name="press" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">作者</label>
                                <div class="col-sm-3">
                                    <input type="text" id="author" name="author" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">库存</label>
                                <div class="col-sm-3">
                                    <input type="text" id="total" name="total" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">位置</label>
                                <div class="col-sm-2">
                                    <select id="floor" name="floor" class="form-control">
                                        <c:forEach var="i" begin="1" end="10" step="1">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <b class="help-block">楼</b>
                                </div>
                                <div class="col-sm-2">
                                    <select id="bookcase" name="bookcase" class="form-control">
                                        <c:forEach var="i" begin="1" end="10" step="1">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <b class="help-block">书架</b>
                                </div>
                                <div class="col-sm-2">
                                    <select id="layer" name="layer" class="form-control">
                                        <c:forEach var="i" begin="1" end="10" step="1">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <b class="help-block">层</b>
                                </div>
                                <div class="col-sm-1">
                                    <b class="help-block">*必填</b>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="brief" class="control-label col-lg-2">是否上架</label>
                                <div class="col-sm-2">
                                    <select id="display" name="display" class="form-control">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="brief" class="control-label col-lg-2">简介</label>
                                <div class="col-lg-5">
                                    <textarea class="form-control " id="brief" name="brief" rows="10"></textarea>
                                </div>
                            </div>
                            <div class="form-group last" id="fileZero">
                                <label class="control-label col-md-3">图书封面上传</label>
                                <div class="col-md-9">
                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                            <img id="fileImg"
                                                 src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image"
                                                 alt=""/>
                                        </div>
                                        <div class="fileupload-preview fileupload-exists thumbnail"
                                             style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                        <div>
                                            <span class="btn btn-theme02 btn-file">
                                                <span class="fileupload-new"><i class="fa fa-paperclip"></i> 选择图片</span>
                                            <span class="fileupload-exists"><i class="fa fa-undo"></i> 更改</span>
                                            <input name="file" id="file" type="file" class="default"
                                                   onchange="checkFile(this)"/>
                                            </span><a class="btn btn-theme04 fileupload-exists"
                                                      data-dismiss="fileupload"><i class="fa fa-trash-o"></i> 删除</a>
                                        </div>
                                    </div>
                                    <span class="label label-info">注意！</span>
                                    <span>只允许大小为1M以下,类型为jpg,png,gif的文件</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button id="save" class="btn btn-theme" type="submit">保存</button>
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
<script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="background/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="background/lib/jquery.scrollTo.min.js"></script>
<script src="background/lib/jquery.nicescroll.js" type="text/javascript"></script>
<!--common script for all pages-->
<script src="background/lib/common-scripts.js"></script>
<!--script for this page-->
<script src="background/lib/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="background/lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script src="background/lib/form-validation-script.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>
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
    $.ajax({
            url: "/book/getAllTypes",
            success: function (data) {
                for (var type of data)
                    $("#booktypeid").append(`<option value=` + type.id + `>` + type.booktype + `</option>`)
            }
    });
    $(document).ready(function () {
        $("#addBookForm").validate({
            rules: {
                bookname: {
                    required: true,
                    maxlength: 50,
                },
                booktypeid: {
                    required: true
                },
                press: {
                    required: true,
                    maxlength: 50,
                },
                total: {
                    required: true,
                    digits: true,
                    maxlength: 4,
                },
                floor: {
                    required: true,
                    digits: true
                },
                bookcase: {
                    required: true,
                    digits: true
                },
                level: {
                    required: true,
                    digits: true
                },
                isbn: {
                    required: true,
                    digits: true,
                    maxlength: 20,
                },
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    url: "/book/addBook",
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        if (data.result === "success") {
                            alert("添加图书成功");
                            window.open('/Book_table', '_self');
                        }
                        else {
                            alert(data.msg);
                        }
                    },
                    error: function () {
                        alert("网络出现问题！");
                    }
                });
            }
        });
    });

    $("#autoFill").on("click", function () {
        isbn = $("#isbn").val();
        $.ajax({
            url: 'https://api.douban.com/v2/book/isbn/' + isbn,
            type: 'get',
            dataType: 'jsonp',//解决跨域问题
            success: function (data) {
                //清空
                $("#bookname").val();
                $("#press").val();
                $("#author").val();
                $("#brief").val();

                $("#bookname").val(data.title);
                $("#press").val(data.publisher);
                var author = data.author;
                if (author.length != 0) {
                    $("#author").val(data.author[0]);
                } else {
                    $("#author").val("暂无");
                }
                $("#brief").val(data.summary);

            },
            error: function () {
                alert("找不到符合的图书")
            }
        });

    });

    function checkFile(target) {
        var file = $("#file");
        var name = file.val();
        var type = name.substring(name.lastIndexOf(".") + 1, name.length).toLocaleLowerCase();
        var size = $("#file")[0].files[0].size;
        console.log(type);
        if (type !== "jpg" && type !== "png" && type !== "gif") {
            alert("只支持jpg,png,gif文件！");
            file.outerHTML = file.outerHTML;
            file.val("");
            $(".fileupload-preview img").attr("src", "http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image");
            return false;
        }
        if (size > 1024000) {
            alert("文件大小不超过1M");
            file.outerHTML = file.outerHTML;
            file.val("");
            $(".fileupload-preview img").attr("src", "http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image");
            return false;
        }
    }
</script>
</body>

</html>