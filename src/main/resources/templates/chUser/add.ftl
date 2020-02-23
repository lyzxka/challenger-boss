<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户表添加--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="${site.description}"/>
    <meta name="keywords" content="${site.keywords}"/>
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="phone"  placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="email"  placeholder="请输入邮箱">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name"  placeholder="请输入昵称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">头像</label>
        <div class="layui-input-block">

            <input type="hidden" class="layui-input" name="icon" id="icon" >
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="test_icon">上传头像</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo_icon">
                    <p id="demoText_icon"></p>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="password"  placeholder="请输入密码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加密盐</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="salt"  placeholder="请输入加密盐">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addChUser">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','upload'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                upload = layui.upload,
                layer = layui.layer;

                        //普通图片上传
        var upload_icon = upload.render({
            elem: '#test_icon',
            url: '${base}/file/upload/',
            field:'test',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo_icon').attr('src', result); //图片链接（base64）
                });
            },
            done: function(res){
                //如果上传失败
                if(res.success == false){
                    return layer.msg('上传失败');
                }
                $("#icon").val(res.data.url);
            },
            error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText_icon');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    upload_icon.upload();
                });
            }
        });

        form.on("submit(addChUser)",function(data){
   
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/chUser/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("用户表添加成功！",{time:1000},function(){
                        parent.layer.close(parent.addIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>