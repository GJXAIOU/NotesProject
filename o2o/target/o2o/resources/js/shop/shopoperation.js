// 主要用户获取 html 中信息
// 第一个首先获取 商品分类、所属区域等列表的信息
// 第二个就是提交的时候，将整个表单中的内容全部获取到，然后将它通过 Ajax 转发到后台

$(function() {


    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var editShopUrl = '/o2o/shop/registershop';

    // js 文件刚刚加载的时候，调用该方法获取后台的区域信息，并将其填充到前端  -> 在 HTML 尾部引入 js 文件
    getShopInitInfo();

    function getInfo(shopId) {
        $.getJSON(initUrl, function(data) {
            if (data.success) {
                var tempHtml ='';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function(item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });

    $('#submit').click(function() {
        var shop = {};

        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();

        shop.shopCategory = {
            shopCategoryId : $('#shop-category').find('option').not(function() {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId : $('#area').find('option').not(function() {
                return !this.selected;
            }).data('id')
        };

        var shopImg = $("#shop-img")[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));

        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual){
            $.toast("请输入验证码");
            return;
        }
        formData.append("verifyCodeActual",verifyCodeActual);

        $.ajax({
            url : editShopUrl,
            type : 'POST',
            // contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！' + data.errMsg);
                }
                // 无论提交成功或者失败都要换验证码
                $('#captcha_img').click();
            }
        });
    });
    }
});
