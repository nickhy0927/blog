var frameId = "uploadIframe";
(function ($) {
    $.fn.fileupload = function (opts) {
        var settings = $.extend({
            fileId: '',//上传文件的的file的id
            url: '',//上传的地址
            multipart: false,
            autoupload: false,//是否自动上传
            method: 'POST',//以post方式提交
            dataType: 'JSON',// html xml text json
            fileType: '',//文件类型
            fileMaxSize: '',//允许上传的文件的大小
            maxNumber: '',//最大支持多少个文件
            startBtn: '',//点击开始上传文件的按钮的ID
            success: function (data) {
            },
            error: function (data) {
            }
        }, opts || {});

        var id = new Date().getTime();

        //form表单的ID
        var formId = 'jUploadForm' + id;

        //上传文件的ID
        var fileId = 'jUploadFile' + id;

        //用户的上传文件的对象
        var oldElement = $('#' + settings.fileId);
        // 初始化Iframe
        _create_dom(fileId, formId, oldElement, settings);

        // 文件文本框的改变事件
        $("#" + settings.fileId).change(function () {
            var fileVal = $("#" + settings.fileId).val();
            var old = $("#" + settings.fileId);
            var newElement = old.clone();
            newElement.attr("id", fileId);
            $(newElement).appendTo($("#" + formId));
            if (settings.autoupload) {
                showdiv();
                document.getElementById(formId).setAttribute("action", settings.url); //FORM提交到这个页面
                document.getElementById(formId).submit();
            }
            // 开始上传
            $('#' + settings.startBtn).click(function () {
                showdiv();
                document.getElementById(formId).setAttribute("action", settings.url); //FORM提交到这个页面
                document.getElementById(formId).submit();
            });
        });


        //选择文件的按钮的事件
        $(this).click(function () {
            $("#" + settings.fileId).click();
        });


        $("#" + frameId).load(function () {
            // 获得iframe中的内容
            var html = $("#" + frameId).contents().find("body").html();
            // 获取HTML中的纯文本
            var result = $(html).text();
            try {
                result = jQuery.parseJSON(result);
            } catch (err) {
                settings.error(result);
            }
            settings.success(result);
            var state = null;
            if (document.readyState) {
                try {
                    state = $(this).document.readyState;
                } catch (e) {
                    state = null;
                }
                if (state == "complete" || !state) {
                    console.log('加载完成....');
                    $("#showText").text("上传完成.");
                    $("#showClose").css('display', 'block');
                    return;
                }
            }
        });
    }
})(jQuery);

/*创建form表单*/
function _create_form(fileId, formId, oldElement, settings) {
   /* var input = "<input type='file' style='display:none' name='" + oldElement.attr("name") + "[]' id='" + fileId + "'/>";
    if (settings.multipart) {
        $(input).attr("multiple", "multiple");
    }*/
    var form = "<form style='display:none' enctype='multipart/form-data' name='" + formId + "' id='" + formId + "' method=\"post\" "
        + "target='" + frameId + "' onsubmit='return false;'>"
        + "</form>";
    return form;
}


function _create_dom(fileId, formId, oldElement, settings) {
    var form = _create_form(fileId, formId, oldElement, settings);
    var iframe = _create_iframe();
    var show = '<div id="show">'
        + '<div class="dialog-title">'
        + '提示信息'
        + '<a id="showClose" href="javascript:hidediv()" class="dialog-close">关闭</a>'
        + '</div>'
        + '<div id="showText" class="dialog-content">'
        + '正在上传，请稍等...'
        + '</div>'
        + '</div>';
    var bg = '<div id="bg"></div>';
    $("body").append(iframe).append(form).append(bg).append(show);
}

/*创建iframe*/
function _create_iframe() {
    var iframe = "<iframe id='" + frameId + "' name='" + frameId + "' style=\"display:none\"></iframe>";
    return iframe;
}

var frame = _create_iframe()


function showdiv() {
    document.getElementById("bg").style.display = "block";
    document.getElementById("show").style.display = "block";
}
function hidediv() {
    document.getElementById("bg").style.display = 'none';
    document.getElementById("show").style.display = 'none';
}
