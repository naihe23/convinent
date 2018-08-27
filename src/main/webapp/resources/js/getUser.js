//获取用户的数据、设置到页面的hidden上
$(function () {
    $.ajax({
        url: "/user/getUser.do",
        type: "post",
        async: false,
        data: {},
        success: function (responseText) {
            if (responseText != null) {
                $("#loginDiv div h1").html("<font color='red'>" + responseText.userNickname + "</font>");
                $("#loginDiv").attr("href", "");

                $("#registerDiv").attr("href","/logout.do");
                $("#registerDiv div h1").html("注销");

                //页面获取用户的数据
                $("#userNickname").val(responseText.userNickname);
                $("#userId").val(responseText.userId);


                $("#userEmail").val(responseText.userEmail);
            }
        },
        error: function () {


            alert("系统错误");
        }
    })
});
