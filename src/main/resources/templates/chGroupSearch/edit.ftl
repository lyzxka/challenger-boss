<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>组队请求编辑--${site.name}</title>
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
    <input value="${chGroupSearch.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">队伍</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${chGroupSearch.groupId}" name="groupId"  placeholder="请输入队伍">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${chGroupSearch.title}" name="title"  placeholder="请输入标题">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">比赛</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${chGroupSearch.matchId}" name="matchId"  placeholder="请输入比赛">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详情</label>
        <div class="layui-input-block">
                <textarea name="content"   placeholder="请输入详情" class="layui-textarea">${chGroupSearch.content}</textarea>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">成员人数</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${chGroupSearch.userNum}" name="userNum"  placeholder="请输入成员人数">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类别</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${chGroupSearch.categoryId}" name="categoryId"  placeholder="请输入类别">

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addChGroupSearch">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                layer = layui.layer;


        form.on("submit(addChGroupSearch)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/chGroupSearch/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("组队请求编辑成功！",{time:1000},function(){
                        parent.layer.close(parent.editIndex);
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