/**
 * Created by zhangyw on 2017/8/14.
 */

$(function () {

    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };

    var topic = new Array();

    $(".topic-labels").find(".label").click(function () {
        const ele = $(this);
        if(ele.hasClass("outline")){
            ele.removeClass("outline");
            topic.push(ele.find("span").attr("topic-id"));
        }else{
            ele.addClass("outline");
            topic.remove(ele.find("span").attr("topic-id"));
        }
    });

    /**
     * 订阅
     */
    $("#emailBut").click(function () {
        console.log(topic);
        const email = $("#email").val();
        if(!is.email(email)){
            layer.msg("请输入正确的邮箱地址");
        }else if(topic.length == 0){
            layer.msg("请至少选择一个分类订阅");
        }else{
            const index = layer.load();
            $.ajax({
                type: "POST",
                url: "/subscribe",
                data: {
                    email: email,
                    topics: topic.join(",")
                },
                success: function (data) {
                    layer.close(index);
                    if(data.code == 200){
                        layer.msg("订阅成功，请登录邮箱进行验证！！！");
                    }else{
                        layer.msg(data.message);
                    }
                    $("#email").val("");
                }
            })
        }
    })

    /**
     * 提交网址
     */
    $("#urlBut").click(function () {
        const url = $("#url").val();
        if(!is.url(url)){
            layer.msg("请输入正确的网址");
        }else{
            const index = layer.load();
            $.ajax({
                type: "POST",
                url: "/resources",
                data: {
                    site: url
                },
                success: function (data) {
                    layer.close(index)
                    if(data.code == 200){
                        layer.msg("非常！非常！感谢您为我们添砖加瓦，感谢！", {icon: 1});
                    }else{
                        layer.msg(data.message);
                    }
                    $("#url").val("");
                }
            })
        }
    })

});
