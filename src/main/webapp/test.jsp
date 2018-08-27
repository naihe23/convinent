<%--
  Created by IntelliJ IDEA.
  User: sywong
  Date: 2018-08-06
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.css">
</head>
<body>
<button class="btn btn-primary" data-toggle="modal" data-target="#mymodal-data" type="button">通过data-target触发</button>
<div>
    <img src="/user/getCode" alt="验证码">
</div>
<!-- 模态弹出窗内容 -->
<div class="modal fade" id="mymodal-data" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">模态弹出窗标题</h4>
            </div>
            <div class="modal-body">
                <p>模态弹出窗主体内容</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
