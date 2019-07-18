layui.use('layer', function () {
    var $ = layui.$;

    //用于设置全局的ajax默认值，complete在ajax success/error后回调
    //设置此方法的目的是session过期后，对于ajax请求没有响应，无法返回登录页；浏览器地址访问可行。
    //因此在拦截器针对于ajax的session过期做特殊处理，响应头返回timeout，浏览器的顶级窗口刷新返回登录页
    $.ajaxSetup({
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 403) {
                alert('您没有权限访问此资源');
                return false;
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");
            var authorityStatus = XMLHttpRequest.getResponseHeader("authorityStatus");
            if (sessionStatus == 'timeout') {
                var top = getTopWindow();
                top.location.href = "/web/login";
            }
            if (authorityStatus == "forbidden") {
                var top = getTopWindow();
                top.location.href = "/web/403";
            }
        }
    });
});

//找到顶级窗口
function getTopWindow() {
    var p = window;
    while (p != p.parent) {
        p = p.parent;
    }
    return p;
}