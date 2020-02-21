$(function () {
    //重新加载的时间
    setInterval("slidePhotos()", 5000);
});

//轮播的图片
var i = 0;
var arr = new Array();

arr[0] = "/static/img/banner1.jpg";
arr[1] = "/static/img/banner2.jpg";

function slidePhotos() {
    var photo = document.getElementById("slide_photo");
    if (i == arr.length - 1) {
        i = 0;
    } else {
        i += 1;
    }
    photo.src = arr[i];    //直接定义图片
}

function linkFruit() {
    window.location.href = "https://sdcloud.chidaoni.com/o/waimai/?restId=1001858&brandId=1001857&groupId=0&source=url";
}

function closeTemp() {
    $.alert("敬请期待。。。");
}



