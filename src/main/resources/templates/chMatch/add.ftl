<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>比赛添加--${site.name}</title>
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
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="title"  placeholder="请输入标题">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">门类</label>
        <div class="layui-input-block">
            <select name="categoryId" lay-search>
                <option value="" selected="">请选择类别</option>
                <#list categoryList as r>
                    <option value="${r.id}" >${r.categoryName}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">主图</label>
        <div class="layui-input-block">

            <input type="hidden" class="layui-input" name="imgUrl" id="imgUrl" >
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="test_imgUrl">上传主图</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo_imgUrl">
                    <p id="demoText_imgUrl"></p>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">

            <textarea name="content"  placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">

            <input type="text" name="beginDate" id="beginDate"   lay-verify="date" placeholder="请选择开始时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">截止时间</label>
        <div class="layui-input-block">

            <input type="text" name="endDate" id="endDate"   lay-verify="date" placeholder="请选择截止时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addChMatch">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','upload','laydate'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                upload = layui.upload,
                laydate = layui.laydate,
                layer = layui.layer;

                        //普通图片上传
        var upload_imgUrl = upload.render({
            elem: '#test_imgUrl',
            url: '${base}/file/upload/',
            field:'test',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo_imgUrl').attr('src', result); //图片链接（base64）
                });
            },
            done: function(res){
                //如果上传失败
                if(res.success == false){
                    return layer.msg('上传失败');
                }
                $("#imgUrl").val(res.data.url);
            },
            error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText_imgUrl');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    upload_imgUrl.upload();
                });
            }
        });
                          //初始赋值
                          laydate.render({
                            elem: '#beginDate'
                          });
                          //初始赋值
                          laydate.render({
                            elem: '#endDate'
                          });

        form.on("submit(addChMatch)",function(data){
                          if(null === data.field.beginDate || "" ===data.field.beginDate){
                        delete data.field["beginDate"];
                    }else{
                        data.field.beginDate = new Date(data.field.beginDate);
                    }
                       if(null === data.field.endDate || "" ===data.field.endDate){
                        delete data.field["endDate"];
                    }else{
                        data.field.endDate = new Date(data.field.endDate);
                    }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/chMatch/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("比赛添加成功！",{time:1000},function(){
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