<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>忘记密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loginUser.css">
</head>
<style>
    /*登陆的超链接往下调*/
    .btn-primary {
        color: #fff;
        background-color: #337ab7;
        border-color: #2e6da4;
        margin-bottom: 15px;
    }
</style>
<body>
<div id="particles-js">
    <div class="container" style="position: absolute;left: 120px;top: 70px">
        <form class="form-signin" action="${request.contextPath}/user/forgetPassword.do" method="post" id="loginForm">

            <h2 class="form-signin-heading">忘记密码</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="userEmail" id="userEmail" placeholder="邮箱地址"/>
            </div>
            <div class="form-group">
                <input class="btn  btn-primary btn-block" type="submit" class="form-control" value="重置密码"/>
            </div>
            <div class="form-group">
                <a href="${request.contextPath}/user/login">登陆</a>
                &nbsp; |&nbsp;
                <a href="${request.contextPath}/user/register">注册</a>
            </div>
        </form>
    </div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/particles.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/forgetPassword.js"></script>
</body>
</html>

