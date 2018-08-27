<%--
  Created by IntelliJ IDEA.
  User: sywong
  Date: 2018-08-05
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人书签</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/content.css">
</head>
<style type="text/css">
    .row{
        top: 120px;
        left: 450px;
    }
</style>
<body>
<div id="particles-js">
    <jsp:include page="narbar.jsp"/>

    <div class="row " style="position: absolute; margin:0px auto;
text-align:center; ">
        <div class="col-lg-6 gover_search">
            <div class="input-group gover_search_form clearfix">

                <input class="form-control input-lg " type="text" placeholder="请输入您自定义的网站名..." id="condition">
                <span class="input-group-btn" style="padding-left: 5px">
                    <button class="btn btn-primary btn-lg" type="button" id="search">搜索</button>
                </span>
                <div class="search_suggest" id="gov_search_suggest">
                    <ul>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div style="position: absolute;padding-top: 120px;margin-left: 100px">
        <button type="button" class="btn btn-primary" data-toggle="modal"
                data-target="#addSiteModel">增加网站
        </button>
        <button type="button" class="btn btn-primary" id="queryById" data-toggle="modal"
                data-target="#manageSite">删除网站
        </button>
    </div>

    <div class="modal fade" aria-hidden="true" data-backdrop="static" id="addSiteModel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">增加网站</h4>
                </div>
                <!--模态框中的内容-->
                <div class="modal-body">
                    <form class="form-horizontal" id="siteForm">

                        <input type="hidden" id="userId" name="userId">
                        <div class="form-group">
                            <label for="inputSiteAddr" class="col-sm-2 control-label">网站地址</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputSiteAddr"
                                       placeholder="请输入完整的网站地址(包括http(s)://)"
                                       name="webSiteAddr">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputSiteName" class="col-sm-2 control-label">命名</label>
                            <div class="col-sm-10">

                                <input type="text" name="webSiteName" class="form-control" id="inputSiteName"
                                       placeholder="请为该网站命名(为了可以方便查找)">
                            </div>

                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="addSite">确定</button>

                </div>
            </div>
        </div>
    </div>

    <!--删除网站模态框避免点击背景处关闭,aria-hidden="true" data-backdrop="static" -->
    <div class="modal fade " aria-hidden="true" data-backdrop="static" id="manageSite">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">管理网站</h4>
                </div>
                <!--模态框中的内容-->
                <div class="modal-body">

                    <table class="table table-hover">
                        <tr id="manageSiteTr">
                            <td>网站完整地址：</td>
                            <td>网站命名：</td>
                            <td>操作：</td>
                        </tr>
                        <input type="hidden" id="manageSiteContent">
                    </table>


                  <%--  <!--分页-->
                    <input type="hidden" id="currentPage">
                    <input type="hidden" id="totalPageCount">
                    <input type="hidden" id="totalRecordCount">

                    <ul class="pagination">
                        <li class="disabled"><a href="#!"><i class="material-icons">上一页</i></a></li>
                        <li class="active"><a href="#!">1</a></li>
                        <li class="waves-effect"><a href="#!">2</a></li>
                        <li class="waves-effect"><a href="#!">3</a></li>
                        <li class="waves-effect"><a href="#!">4</a></li>
                        <li class="waves-effect"><a href="#!">5</a></li>
                        <li class="waves-effect"><a href="#!"><i class="material-icons">下一页</i></a></li>
                    </ul>--%>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="addSite1">确定</button>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/particles.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/header.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/modelCenter.js"></script>
</body>
</html>
