function syborderpay() {
    $.ajax({
            url: '/syb/pay',
            type: 'get',
            data: {},
            dataType: 'json',
            success: function (data) {
                alert(data);
            },
            error: function (data) {
                alert(data);
            }
        }
    )
}