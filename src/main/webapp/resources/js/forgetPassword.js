
$(function () {
    //bootstrap校验
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userEmail: {
                message: '邮箱验证失败',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            }
        },
        submitHandler: function (validator, form, submitButton) {
            var $email = $("#userEmail").val();
            //ajax请求
            $.ajax({
                url: "/user/validateEmail.do",
                type: "post",
                async: false,
                data: {
                    "userEmail":$email
                },
                success: function (responseText) {
                    if (responseText == "noEmail") {
                        alert("用户未激活,请到您指定的邮箱完成激活");
                    } else if (responseText == "nullEmail") {
                        alert("您的邮箱未注册");
                    }else if (responseText == "hasEmail"){
                        validator.defaultSubmit();
                        alert("请到指定的邮箱完成密码修改 ");
                    }
                },
                error: function (response, ajaxOptions, thrownError) {
                    alert("系统错误");
                }
            });
        }
    });
});