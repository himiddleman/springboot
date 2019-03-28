$(function () {
    $("#username").val($.trim(localStorage.getItem("username")));
    $("#password").val($.trim(localStorage.getItem("password")));
})

function login() {
    var email, password;
    //用户保存的账号信息
    var storedUsername = $.trim(localStorage.getItem("username"));
    var storedPassword = $.trim(localStorage.getItem("password"));
    if (storedUsername != "" && storedPassword != "") {
        email = storedUsername;
        password = storedPassword;
    } else {
        email = $.trim($("#username").val());
        password = $.trim($("#password").val());
    }
    //参数校验
    if ($.trim(email) == "") {
        $("#errorMsg").html("账号不能为空！").css({"color": "#ff0011"}).show().delay(3000).hide(0);
        return false;
    }
    if ($.trim(password) == "") {
        $("#errorMsg").html("密码不能为空！").css({"color": "#ff0011"}).show().delay(3000).hide(0);
        return false;
    }
    $.ajax({
        url: '/user/getAdmin',
        type: 'post',
        data: {
            email: email,
            password: password
        },
        dataType: 'json',
        success: function (data) {
            if (data.code == "200") {
                //勾选了
                if (($("input[name='remember']")).is(":checked")) {
                    localStorage.setItem("username", data.data.email);
                    localStorage.setItem("password", data.data.password);
                }
                window.location.href = "/web/home";
            } else {
                $("#errorMsg").html("账号或密码错误！").css({"color": "#ff0011"}).show().delay(3000).hide(0);
            }
        },
        error: function () {
            layer.alert("登录失败，请重试！");
        }
    })
}