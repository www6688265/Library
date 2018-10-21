<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
  <title>图书管理系统登录</title>

  <!-- Favicons -->
    <link href="background/img/favicon.png" rel="icon">
    <link href="background/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
    <link href="background/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
    <link href="background/lib/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <!-- Custom styles for this template -->
    <link href="background/css/style.css" rel="stylesheet">
    <link href="background/css/style-responsive.css" rel="stylesheet">
</head>

<body>
 <div id="login-page">
    <div class="container">
      <form class="form-login" method="post" id="data">
        <h2 class="form-login-heading">sign in now</h2>
        <div class="login-wrap">
          <input type="text" id="idcard" name="idcard" class="form-control" placeholder="管理员用户名" autofocus>
          <br>
          <input type="password" id="admpassword" name="admpassword" class="form-control" placeholder="密码">
          <br>
          <button id="login"class="btn btn-theme btn-block" type="button">
              <i class="fa fa-lock"></i> 登录</button>
          <br>
        </div>
      </form>
    </div>
  </div>
  <!-- js placed at the end of the document so the pages load faster -->
 <script src="background/lib/jquery/jquery.min.js"></script>
 <script src="background/lib/bootstrap/js/bootstrap.min.js"></script>
  <!--BACKSTRETCH-->
  <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
 <script type="text/javascript" src="background/lib/jquery.backstretch.min.js"></script>
  <script>
      $.backstretch("background/img/login-bg.jpg", {
      speed: 500
    });
    $(document).ready(function() {
        $("#login").click(function () {
            $.ajax({url:"/admin/login",
                type:"POST",
                dataType:"json",
                data:$("#data").serialize(),
                success:function (data) {
                        if(data.result=="success"){
                            alert("登录成功");
                            window.location.href = "${pageContext.request.contextPath}/index";
                        }
                        else{
                            alert(data.result);
                        }
                },
                error:function () {
                    alert("网络出现问题！");
                }
            })
        });
    });
  </script>
</body>

</html>
