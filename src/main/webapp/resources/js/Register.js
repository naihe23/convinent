/*表单验证以及提交------------------------start*/
$(function () {
    //bootstrap校验
    $('#sform').bootstrapValidator({
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
            },
            userPassword: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 12,
                        message: '密码长度必须在6到12位之间'
                    },
                }
            },
            userNickname: {
                message: '昵称验证失败',
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '昵称长度必须在2到15位之间'
                    },
                }
            }
        }
    });
});

/*表单验证以及提交------------------------end*/

$(document).ready(function () {
    $("#userEmail").blur(function () {
        var $email = $("#userEmail").val();
        $.ajax({
            url: "/user/validateEmail.do",
            type: "post",
            async: false,
            data: {
                "userEmail": $email
            },
            success: function (responseText) {
                if (responseText == "noEmail") {
                    alert("用户未激活,请到您指定的邮箱完成激活");
                } else if (responseText == "hasEmail") {
                    alert("您的邮箱已注册过了");
                }
            },
            error: function () {
                alert("系统错误！");
            }
        });
    });
})