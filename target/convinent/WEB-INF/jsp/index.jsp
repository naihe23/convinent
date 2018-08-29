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
    <style>
        .jumbotron {
            padding-top: 115px;
            padding-bottom: 30px;
            margin-bottom: 30px;
            color: #c7c7c7;
            background-color: #242522;
        }
        .jumbotron h1 {
            color: inherit;
            margin-bottom: inherit;
        }
    </style>
</head>
<body>
<div id="particles-js">
<jsp:include page="narbar.jsp"/>
    <div class="jumbotron text-center" style="position: absolute;width: 100%" id="weather">
        <h1></h1>

    </div>
</div>


<script src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/particles.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/header.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/getUser.js"></script>
<script>
    $(function () {
        /*先获取位置、再获取天气预报信息*/
        $.ajax({
            url: "http://restapi.amap.com/v3/ip?key=11ddf2f282ee4c39d35a46c3a4dae845",
            type: "get",
            success: function (responseText1) {
                $.ajax({
                    url: "https://free-api.heweather.com/s6/weather/forecast?key=d66df9e9bec5484da78f88a5bb58d092&location=" + responseText1.city,
                    type: "get",
                    success: function (responseText2) {

                        $(".jumbotron h1").html(responseText1.city + "近三日天气");

                        var jsonObj = responseText2.HeWeather6[0].daily_forecast;
                        for (var i = 0; i < jsonObj.length; i++) {


                            var date = jsonObj[i].date;
                            var weather = jsonObj[i].cond_txt_d;
                            var low = jsonObj[i].tmp_min;
                            var hight = jsonObj[i].tmp_max;

                            $(".jumbotron").append("<p>" + date + "：" + weather + "，温度：" + low + "～" + hight + "℃</p>");

                        }
                    },
                    error: function () {
                        alert("获取天气失败...")
                    }
                });

            },
            error: function () {
                alert("定位失败...")
            }
        });
    });
</script>
</body>
</html>

