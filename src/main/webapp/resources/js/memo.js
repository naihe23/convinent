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

$(function () {
    $("#datetimepicker").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,//自动关闭
        minView: 0,//最精准的时间选择为日期0-分 1-时 2-日 3-月
        weekStart: 0,
        startDate: new Date()
    });
});

$(function () {
   $.ajax({
      url:"/memo/queryAllMemo",
      method:"post",
      data:{userId:$("#userId").val()},
      success:function (responseText) {
          if(responseText != null){
               var nickName = responseText.nickName;
               var email = responseText.email;
               var memoList = responseText.list;
              for (var i = 0; i < memoList.length; i++) {
                  var memoId = memoList[i].memoId;
                  $("#allMemo").append("<tr><td>" + nickName + "</td><td>" + new Date(memoList[i].editTime).toLocaleString() + "</td><td>" + email + "</td><td>" + memoList[i].memoContent + "</td><td>" + new Date(memoList[i].sendTime).toLocaleString() + "</td><td>" + memoList[i].state + "</td><td><button class='btn btn-primary' onclick='updCurrentMemo(this)' id=" + memoId + "  data-toggle='modal' data-target='#mymodal'>修改</button>&nbsp;&nbsp;<a href='/memo/deleteMemo?memoId=" + memoId + "'>删除</a></td>");


                  //如果是已发送状态的，那么设置不可用
                  if (memoList[i].state == 1) {
                      $("#" + memoId + "").attr("disabled", "true");

                  }
              }
          }else
              alert("null");
      },
       error:function () {
           alert("系统错误");
       }
   });
});

Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
};

function updCurrentMemo(currentMemo) {


    var id = $(currentMemo).attr("id");

    //获取内容
    var content = $(currentMemo).parent().siblings().eq(3).html();

    //获取发送时间
    var sendTime = $(currentMemo).parent().siblings().eq(4).html();

    $("#memoContentManager").empty();

    //将其添加到模态框中
    $("#memoContentManager").append("<tr><td><input type='text' id='memoContent' class='form-control' value='" + content + "'></td><td><input type='text' id='sendTime' value='" + sendTime + "'  readonly  class='form-control form_datetime '></td><input type='hidden' value='" + id + "' id='memoId'></tr>");


    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,//自动关闭
        minView: 0,//最精准的时间选择为日期0-分 1-时 2-日 3-月
        weekStart: 0,
        startDate: new Date()

    });
}

$(function () {
    $("#updateMemo").click(function () {

        var memoContent = $("#memoContent").val();
        var sendTime = $("#sendTime").val();
        var memoId = $("#memoId").val();
        var userId = $("#userId").val();


        $.ajax({
            url: "/memo/editMemo",
            type: "post",
            data: {memoId: memoId, memoContent: memoContent, sendTime: sendTime, userId: userId},

            success: function (responseText) {
                if (responseText == "success") {
                    alert("修改成功");
                } else {
                    alert("修改失败");
                }

                //隐藏模态框并刷新页面
                $('#myModal').modal('hide');
                window.location.reload();
            },
            error: function () {
                sweetAlert("系统错误了");
            }
        });


    });
});