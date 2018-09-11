$(function () {
    $("#addSite").click(function () {
        $.ajax({
           url:"/bookmark/addWeb" ,
            type:"post",
            data:$("#siteForm").serialize(),
            success:function (responseText) {
                if(responseText == "notNull") {
                    alert("网站名或者网站地址不能为空!");
                }else if(responseText == "success"){
                    $("#inputSiteAddr").val("");
                    $("#inputSiteName").val("");
                    alert("添加成功!");
                }else if(responseText == "hasWebName"){
                    $("#inputSiteName").val("");
                    alert("已存在网站名称，不能重复!");
                }else
                    alert("添加失败!");
            }
        });
    });
});

$(function () {
    $("#queryById").click(function () {
        $("#webSiteContent").empty();
       queryWebSite();
    });
});

function queryWebSite() {
    $.ajax({
        url:"/bookmark/queryWeb" ,
        type:"post",
        data:{userId:$("#userId").val()},
        success:function (responseText) {
            for (var index in responseText) {
                    //获取Map的value，index就代表Map的key
                    var jsonObj = responseText[index]
                    //获取JSON对象
                    var data = eval("(" + jsonObj + ")");
                    var index = index;
                    $("#webSiteContent").append("<tr><td><input type='text' value=" + data.websiteAddress + " /></td><td><input type='text' value=" + data.websiteName + " /></td><td><a href='#' onclick=deleteById('" +  index  + "')>删除</a></td></tr>");
            }
        },
        error:function () {
            alert("系统错误")
        }
    });
}

function deleteById(index) {
    $.ajax({
        url:"/bookmark/deleteWeb" ,
        type:"post",
        data:{id:index},
        success:function (responseText) {
            if(responseText == "success"){
                alert("删除成功");
                $("#webSiteContent").empty();
                queryWebSite();
            }else
                alert("删除失败");
        },
        error:function () {
            alert("系统错误")
        }
    });
}

$(function () {
    $("#search").click(function () {
        $("[data-toggle='popover']").popover();
        var webName = $("#condition").val();
        $.ajax({
            url:"/bookmark/queryByName",
            type:"post",
            data: {webSiteName:webName,userId:$("#userId").val()},
            success:function (responseText) {
                if(responseText[0]=="success") {
                    var jsonObj = responseText[1];
                    var data = eval("(" + jsonObj + ")");
                    $("#condition").attr("data-content", data.websiteAddress);
                    $("#condition").popover("show");
                }else if(responseText[0]=="nullName"){
                    alert("请输入网站命名!");
                }else
                    alert("不存在此命名!");
            }
        });
    });
});

