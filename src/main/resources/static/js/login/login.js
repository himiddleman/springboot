//element 展示左边菜单栏; 预加载需要使用的模块
//由于layer弹层依赖jQuery，所以可以直接得到
layui.use(['layer', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;

    //登录
    $("#loginSubmit").on("click", function () {
        if (!checkParams()) {
            return;
        }
        $.ajax({
            type: 'post',
            url: '/sys/login',
            data: $("#loginForm").serialize(),
            dataType: 'json',
            success: function (data) {
                if (data.code === "00000") {
                    sessionStorage.setItem("allinpayAuthority", data.data.authorityList);
                    parent.location.href = '/web/index';
                } else {
                    $("#captcha").show();
                    $("#notice").find("div").html(data.msg);
                    $("#notice").show().delay(3000).hide(0);
                }
            },
            error: function () {
                layer.alert("登录失败，请重试！");
            }
        });
    });

    //监听form表单提交事件 防止页面跳转
    form.on('submit(formFilter)', function (data) {
        return false;
    });

    //更换验证码
    $("#captchaImg").on("click", function () {
        $("#captchaImg").attr("src", "/sys/captcha?t=" + new Date());
    });

    //参数校验
    function checkParams() {
        var username = $("input[name='username']");
        var password = $("input[name='password']");
        var captcha = $("input[name='captcha']");
        if (!$.trim(username.val())) {
            $("#notice").find("div").html("请输入用户名");
            $("#notice").show().delay(3000).hide(0);
            return false;
        }
        if (!$.trim(password.val())) {
            $("#notice").find("div").html("请输入密码");
            $("#notice").show().delay(3000).hide(0);
            return false;
        }
        if ($("#captcha").css("display") != "none" && !$.trim(captcha.val())) {
            $("#notice").find("div").html("请输入验证码");
            $("#notice").show().delay(3000).hide(0);
            return false;
        }
        return true;
    }
});