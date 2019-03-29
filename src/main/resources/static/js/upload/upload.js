function multiUpload() {
    debugger;
    var formData = new FormData($("#uploadForm")[0]);
    //对文件数量做限制，文件大小做限制
    var fileArray = $("#uploadForm").get(0)["upload"].files;
    if (fileArray.length == 0) {
        alert("请选择文件！");
        return;
    }
    if (fileArray.length > 200) {
        alert("文件数量超过200个！");
        return;
    }
    var size = 0;
    for (var i = 0; i < fileArray.length; i++) {
        size += fileArray[i].size;
    }
    if (size > 40000000) {
        alert("文件总大小超过限制40M！");
        return;
    }
    $.ajax({
        url: '/order/collect',
        type: 'post',
        data: formData,
        contentType: false,
        processData: false,
        dataType: 'json',
        success: function (data) {
            if (data.code == "200") {
                console.log(data)
            } else {

            }
        },
        error: function () {

        }
    })
}

function syncData() {

}
