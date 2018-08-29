
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
                    }
                }
            }
        },
        submitHandler: function (validator, form, submitButton) {
            var $email = $("#userEmail").val();
            //ajax请求
            $.ajax({
                url: "/user/edit_password.do",
                type: "post",
                async: false,
                data:$("#loginForm").serialize(),
                success: function (responseText) {
                   if (responseText == "editSuccess"){
                       alert("修改成功");
                   } else if(responseText == "editFail"){
                       alert("修改失败")
                   }else {
                       alert("链接超时！");
                   }
                },
                error: function (response, ajaxOptions, thrownError) {
                    alert("系统错误");
                }
            });
        }
    });
});