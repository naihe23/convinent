<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户登录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loginUser.css">
</head>
<style>
    /*注册超链接的位置往下调*/
    .btn-primary {
        color: #fff;
        background-color: #337ab7;
        border-color: #2e6da4;
        margin-bottom: 15px;
    }

    /*忘记密码超链接往右调*/
    label, .radio label {
        min-height: 20px;
        padding-left: 20px;
        margin-bottom: 0;
        font-weight: 400;
        cursor: pointer;
        margin-right: 79px;
    }
</style>
<body>
<div id="particles-js">
    <div class="container" style="position: absolute;left: 120px;top: 70px">
        <form class="form-signin" action="${request.contextPath}/user/userLogin.do" method="post" id="loginForm">

            <h2 class="form-signin-heading">账户登陆</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="userEmail" id="userEmail" placeholder="邮箱地址"/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="userPassword" placeholder="密码"/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="captcha" id="inputCaptcha" placeholder="验证码"/>
                <img src="${request.contextPath}/user/getCode" id="imgObj">
            </div>

            <div class="checkbox">
                <label>
                    <input type="checkbox" name="rememberMe"> 记住我
                </label>
                <label>
                    <a data-toggle="modal" href="user/forget">忘记密码</a>
                </label>
            </div>
            <button class="btn  btn-primary btn-block" id="submitButton" type="submit">立即登陆</button>
            <div class="form-group">
                <a href="/user/register">注册</a>
            </div>
        </form>
    </div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/particles.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/loginDo.js"></script>
</body>
</html>

