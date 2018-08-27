<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人备忘录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cleanDefault.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/memo.css">
</head>
<body>
<jsp:include page="narbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" id="leftNavBar">
                <li class="active"><a href="#" name="queryMemo">查看所有的备忘录<span class="sr-only">(current)</span></a></li>
                <li><a href="#" name="addMemo">添加备忘录</a></li>
            </ul>
        </div>

        <div>
            <!--查看所有备忘录-->
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="queryMemo">
                <h2 class="sub-header">查看所有备忘录</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>用户</th>
                            <th>编辑该备忘录的时间</th>
                            <th>发送的邮箱地址</th>
                            <th>发送的内容</th>
                            <th>预定发送时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="allMemo">

                        </tbody>
                    </table>
                </div>
            </div>

            <!--增加备忘录-->
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="addMemo" style="display: none">
                <h2 class="sub-header">增加备忘录</h2>
                <form class="form-horizontal" method="post" action="">
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">邮箱地址：</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="inputEmail" readonly>
                        </div>
                    </div>

                    <input type="hidden" name="userId" id="userId">

                    <div class="form-group">
                        <label for="datetimepicker" class="col-sm-2 control-label">选择发送邮件的时间：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="sendTime" id="datetimepicker" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputContent" class="col-sm-2 control-label">备忘录内容：</label>
                        <div class="col-sm-10">
                            <textarea name="memoContent" cols="30" rows="10" id="inputContent"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">增加备忘录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改备忘录</h4>
                </div>
                <div class="modal-body">

                    <div class="table-responsive">

                        <form id="updateForm">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>发送的内容</th>
                                    <th>预定发送时间</th>
                                </tr>
                                </thead>
                                <tbody id="memoContentManager">


                                </tbody>
                            </table>
                        </form>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="updateMemo">确认修改</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/header.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/memo.js"></script>
</body>
</html>

