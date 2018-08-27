$(function () {
    $("#leftNavBar li a ").click(function () {

        //当前样式
        $(this).parent().addClass("active").siblings().removeClass();

        var attr = $(this).attr("name");
        if (attr == "queryMemo") {
            $("#queryMemo").show().siblings().hide();
        } else if (attr == "addMemo") {

            //获取用户的邮箱
            var email = $("#userEmail").val();

            //设置输入框的值
            $("#inputEmail").val(email);

            $("#addMemo").show().siblings().hide();
        }
    });
});