<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loginUser.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrapValidator.min.css">
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
<jsp:include page="narbar.jsp"/>
    <div class="container" style="position: absolute;left: 120px;top: 70px">

        <form class="form-signin" action="/user/registerUser.do" method="post" id="sform">
            <h2 class="form-signin-heading">账户注册</h2>

            <div class="form-group">
                <input type="text" class="form-control" id = "userEmail"  name="userEmail" placeholder="邮箱地址"/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="userPassword" placeholder="密码"/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="userNickname" placeholder="请输入长度不超过15位的昵称"/>
            </div>
            <div class="form-group">
                <button class="btn  btn-primary btn-block" type="submit">立即注册</button>
            </div>
            <div class="form-group">
                <a href="${request.contextPath}/goURL/user/toLogin.do">登陆</a>
            </div>
        </form>

    </div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/particles.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/header.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Register.js"></script>
</body>
</html>

