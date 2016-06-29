$.extend($.fn.validatebox.defaults.rules, {
        img_upload:
        {
            validator: function(value, param)
            {
                var ext = value.substring(value.lastIndexOf(".")+1);
                return /(jpg|jpeg|gif|bmp|png|ico)/i
                        .test(ext);
            },
            message: "只允许上传jpg、jpeg、gif、png、bmp、ico格式的图片"
        }
    })