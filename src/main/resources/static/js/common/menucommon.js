//根据登录的用户的角色信息生成特定的菜单 有新的菜单时需要更新
layui.use(['element', 'layer'], function () {
    var $ = layui.$;

    //暂时控制到菜单一层，对于按钮控制，通过后台校验
    var $menu = $("dd[targetAuthority]");
    var $button = $("button[targetAuthority]");
    var $operate = $("div[targetAuthority]");

    var authorityStr = sessionStorage.getItem("allinpayAuthority");

    generateMenuList($menu);
    // generateMenuList($button);
    // generateMenuList($operate);

    function generateMenuList(object) {
        $.each(object, function (index, val) {
            if (authorityStr.indexOf($(object[index]).attr("targetAuthority")) != -1) {
                $(object[index]).show();
            } else {
                $(object[index]).hide();
            }
        })
    }
});