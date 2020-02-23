<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>比赛--${site.name}</title>
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
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <style>
        .detail-body{
            margin: 20px 0 0;
            min-height: 306px;
            line-height: 26px;
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }
        /* blockquote 样式 */
        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }

        /* code 样式 */
        code {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background-color: #f1f1f1;
            border-radius: 3px;
            padding: 3px 5px;
            margin: 0 3px;
        }
        pre code {
            display: block;
        }
    </style>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
  <legend>比赛检索</legend>
  <div class="layui-field-box">
    <form class="layui-form" id="searchForm">
    <div class="layui-inline" style="margin-left: 15px">
            <label>标题:</label>
                <div class="layui-input-inline">
                <input type="text" value="" name="s_title" placeholder="请输入标题" class="layui-input search_input">
                </div>
    </div>
    <div class="layui-inline" style="margin-left: 15px">
            <label>比赛编号:</label>
                <div class="layui-input-inline">
                <input type="text" value="" name="s_matchNo" placeholder="请输入比赛编号" class="layui-input search_input">
                </div>
    </div>
    <div class="layui-inline" style="margin-left: 15px">
            <label>门类:</label>
                <div class="layui-input-inline">
                <input type="text" value="" name="s_categoryId" placeholder="请输入门类" class="layui-input search_input">
                </div>
    </div>
    <div class="layui-inline" style="margin-left: 15px">
            <label>浏览量:</label>
                <div class="layui-input-inline">
                <input type="text" value="" name="s_views" placeholder="请输入浏览量" class="layui-input search_input">
                </div>
    </div>
    <div class="layui-inline" style="margin-left: 15px">
            <label>开始时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginBeginDate" id="beginBeginDate" lay-verify="date"  autocomplete="off" class="layui-input">
                </div>
                <span>-</span>
                <div class="layui-input-inline">
                    <input type="text" name="s_endBeginDate" id="endBeginDate" lay-verify="date"  autocomplete="off" class="layui-input">
                </div>
    </div>
    <div class="layui-inline" style="margin-left: 15px">
            <label>截止时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginEndDate" id="beginEndDate" lay-verify="date"  autocomplete="off" class="layui-input">
                </div>
                <span>-</span>
                <div class="layui-input-inline">
                    <input type="text" name="s_endEndDate" id="endEndDate" lay-verify="date"  autocomplete="off" class="layui-input">
                </div>
    </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="addChMatch">添加比赛</a>
        </div>
    </form>
  </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="imgUrl">
    {{#  if(d.imgUrl != "" && d.imgUrl != null){ }}
    <span id="imgUrl_{{d.id}}" ><img lay-event="imageimgUrl" layer-pid="{{d.id}}" layer-src="{{d.imgUrl}}" src="{{d.imgUrl}}" style="width: 40px;"/></span>
    {{#  } else { }}
    <span ></span>
    {{#  } }}
    </script>
    <script type="text/html" id="content">
    {{#  if(d.content != "" && d.content != null){ }}
    <span><button lay-event="showcontent" class="layui-btn layui-btn-warm layui-btn-sm">显示详情</button></span>
    {{#  } else { }}
    <span ></span>
    {{#  } }}
    </script>
    <script type="text/html" id="userStatus">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.delFlag == false){ }}
        <span class="layui-badge layui-bg-green">正常</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">停用</span>
        {{#  } }}
    </script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table','laydate'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                laydate = layui.laydate,
                table = layui.table;

        var beginBeginDate= laydate.render({//渲染开始时间选择
            elem: '#beginBeginDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endBeginDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var endBeginDate= laydate.render({//渲染结束时间选择
            elem: '#endBeginDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginBeginDate.config.max={
                    year:dates.year,
                    month:dates.month-1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                }
            }
        });
        var beginEndDate= laydate.render({//渲染开始时间选择
            elem: '#beginEndDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endEndDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var endEndDate= laydate.render({//渲染结束时间选择
            elem: '#endEndDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginEndDate.config.max={
                    year:dates.year,
                    month:dates.month-1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                }
            }
        });

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑比赛",
                    type : 2,
                    content : "${base}/chMatch/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回比赛列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === "del"){
                layer.confirm("你确定要删除该比赛么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/chMatch/delete",{"id":data.id},function (res){
                                if(res.success){
                                    layer.msg("删除成功",{time: 1000},function(){
                                        location.reload();
                                    });
                                }else{
                                    layer.msg(res.message);
                                }

                            });
                        }
                )
            }
            if(obj.event == "imageimgUrl"){
                layer.photos({
                    photos: '#imgUrl_'+data.id,
                    anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                });
            }
            if(obj.event === "showcontent"){
                var contentIndex = layer.open({
                    type: 1,
                    title: 'content预览',
                    content: '<div class="detail-body" style="margin:20px;">'+data.content+'</div>'
                });
                layer.full(contentIndex);
            }
        });

        var t = {
            elem: '#test',
            url:'${base}/chMatch/list',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[3,10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {type:'checkbox'},
                {field:'title', title: '标题'},
                {field:'matchNo', title: '比赛编号'},
                {field:'imgUrl', title: '主图',templet:'#imgUrl'},
                {field:'content', title: '内容',templet:'#content'},
                {field:'categoryId', title: '门类'},
                {field:'views', title: '浏览量'},
                {field:'beginDate',  title: '开始时间',templet:'<div>{{ layui.laytpl.toDateString(d.beginDate,"yyyy-MM-dd") }}</div>',unresize: true},
                {field:'endDate',  title: '截止时间',templet:'<div>{{ layui.laytpl.toDateString(d.endDate,"yyyy-MM-dd") }}</div>',unresize: true},
                {field:'delFlag',    title: '比赛状态',width:'12%',templet:'#userStatus'},
                {field:'createDate',  title: '创建时间',width:'15%',templet:'<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',unresize: true}, //单元格内容水平居中
                {fixed: 'right', title:'操作',  width: '15%', align: 'center',toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        var active={
            addChMatch : function(){
                var addIndex = layer.open({
                    title : "添加比赛",
                    type : 2,
                    content : "${base}/chMatch/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回比赛列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }
        };

        $('.layui-inline .layui-btn-normal').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)",function(data){
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });
</script>
</body>
</html>