<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户激活</title>
</head>
<body>
<div style="margin-top: 250px;margin-left: 450px;color:#2e6da4;font-family: Calibri">
    <div style="font-size: 25px">
        ${map.title}
    </div>
    <div style="font-size: 20px">
        ${map.subject}
    </div>
    <div style="font-size: 22px">
        ${map.content}
    </div>
    <div id="div1" style="font-size: 18px;">
    </div>
</div>

<script type="text/javascript">
    /* 开始数倒计时数字 */
    var i = 4;
    function aa(){
        i--;
        div1.innerHTML=i+" 秒钟后，跳转到登录页面";
        if(i<1){
            /* 清除定时器 */
            window.clearInterval(tm);
            window.location.href="login.do";
        }
    }
</script>
<script type="text/javascript">
    /* 做一个定时器 */
    tm = window.setInterval(aa, 1000);
</script>
</body>
</html>

