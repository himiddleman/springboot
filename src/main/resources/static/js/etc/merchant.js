//element 展示左边菜单栏; 预加载需要使用的模块
//由于layer弹层依赖jQuery，所以可以直接得到
layui.use(['table', 'element', 'laypage', 'layer', 'form'], function () {
    var table = layui.table;
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    //抽取查询方法
    var search = function () {
        table.render({
            //表格生成的位置：#ID
            elem: '#test',
            //请求地址
            url: '/web/getList',
            //是否分页
            page: true,
            //请求参数
            where: {
                username: $("#username").val(),
                position: $("#position").val()
            },
            //分页信息
            request: {
                pageName: 'pageNum',
                limitName: 'pageSize'
            },
            //设置返回的属性值，依据此值进行解析
            response: {
                statusName: 'code',
                statusCode: 200,
                msgName: 'msg',
                dataName: 'data'
            },
            //每页展示的条数
            limits: [2, 4, 6],
            //每页默认显示的数量
            limit: 2,
            //单元格设置
            cols: [[
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'username', width: 80, title: '用户名'},
                {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'city', width: 80, title: '城市'},
                {field: 'sign', title: '签名', width: 120},
                {field: 'experience', title: '积分', sort: true},
                {field: 'score', title: '评分', sort: true},
                {field: 'classify', title: '职业'},
                {field: 'wealth', width: 80, title: '财富', sort: true},
                {fixed: 'right', title: '操作', toolbar: '#operator', width: 120}
            ]]
        });
    };
    //页面加载就查询列表
    search();
    //条件查询
    $("#queryBtn").on("click", function () {
        var index = layer.alert("立即提交", function () {
            layer.close(index);
            search();
        })

    });

    //重置参数
    $("#resetBtn").on("click", function () {
        $("#username").val("");
        $("#position").val("");
        layer.alert("参数清除");
    });

    //进行编辑操作
    $("#editSubmit").on("click", function () {
        $.ajax({
            url: '/web/editById',
            type: 'post',
            data: {
                id: '001'
            },
            // async: false,
            dataType: 'json',
            success: function (data) {
                if (data.code == "200") {
                    var index = layer.alert("编辑成功", function () {
                        //点击确认按钮执行回调函数
                        layer.closeAll();
                        search();
                    });
                } else {
                    layer.alert("编辑失败！");
                }
            },
            error: function () {
                layer.alert("编辑失败，请重试！");
            }
        });
    });

    //打开添加页面模态框
    $("#addBtn").on("click", function () {
        openModal("添加", "addForm");
    });

    $("#addSubmit").on("click", function () {
        $.ajax({
            url: '/web/add',
            type: 'post',
            data: {
                id: '001'
            },
            dataType: 'json',
            success: function (data) {
                if (data.code == "200") {
                    var index = layer.alert("添加成功", function () {
                        //点击确认按钮执行回调函数
                        layer.closeAll();
                        search();
                    });
                } else {
                    layer.alert("添加失败！");
                }
            },
            error: function () {
                layer.alert("添加失败，请重试！");
            }
        })
    });

    //监听table行工具事件 如详情、编辑、删除操作
    table.on('tool(test)', function (obj) {
        //获取所在行的数据
        var data = obj.data;
        //删除
        if (obj.event === 'del') {
            var index = layer.confirm('确定删除？', function () {
                $.ajax({
                    url: '/web/delById',
                    type: 'post',
                    data: {
                        id: data.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == "200") {
                            layer.close(index);
                            search();
                        } else {
                            layer.alert("删除失败！");
                        }
                    },
                    error: function () {
                        layer.alert("删除失败，请重试！");
                    }
                });
            });
            //编辑
        } else if (obj.event === 'edit') {
            //先通过后台查询数据，渲染页面后打开模态框
            $.ajax({
                url: '/web/getById',
                type: 'get',
                data: {
                    id: data.id
                },
                dataType: 'json',
                success: function (data) {
                    if (data.code == "200") {
                        var myData = data.data[0];
                        $("#editUsername").val(myData.username);
                        $("#editExperience").val(myData.experience);
                        $("#editScore").val(myData.score);
                        $("#editWealth").val(myData.wealth);
                    } else {
                        layer.alert("查询失败！");
                    }
                },
                error: function () {
                    layer.alert("查询失败，请重试！");
                }
            });
            //打开模态框
            openModal("编辑", "editForm");
        }
    });

    //监听form表单提交事件 防止页面跳转
    form.on('submit(editFilter)', function (data) {
        return false;
    });

    form.on('submit(addFilter)', function (data) {
        return false;
    });

    //打开模态框
    function openModal(operateName, modalName) {
        layer.open({
            title: operateName,
            content: $('#' + modalName),
            area: ['700px', '450px'],
            //点击遮罩关闭窗口
            shadeClose: true,
            //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1
        });
    }
});