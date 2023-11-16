<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="/employee/css/pintuer.css">
    <link rel="stylesheet" href="/employee/css/admin.css">
    <link rel="stylesheet" href="/employee/layui/css/layui.css">
    <script src="/employee/layui/layui.js" type="application/javascript"></script>
    <script src="/employee/js/jquery.js"></script>
    <script src="/employee/js/pintuer.js"></script>
    <style>
        .item label {
            display: inline-block;
            width: 100px;
            height: 30px;
            text-align: center;
            color: #fff;
            line-height: 30px;
            background-color: skyblue;
            border-radius: 5px;
            cursor: pointer;
        }

        .item input {
            display: none;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil"></span> 个人信息完善</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="">
            <div class="form-group">
                <div class="label">
                    <br/>
                    <label>头像：</label>
                </div>
                <div class="field">
                    <div class="item">
                        <br/>
                        <button type="button" class="layui-btn" id="test1">
                            <i class="layui-icon">&#xe67c;</i>上传图片
                        </button>
                        <c:if test="${not empty user.img}">
                            <img id="img" style="position:absolute;left:120px;bottom: -10px; width: 90px;height: 90px;border-radius: 100%"
                                 src="${user.img}"/>
                        </c:if>

                    </div>
<%--                    <script type="text/javascript">--%>
<%--                        //选择图片，马上预览--%>
<%--                        function xmTanUploadImg(obj) {--%>
<%--                            var file = obj.files[0];--%>
<%--                            var reader = new FileReader();--%>
<%--                            reader.onload = function (e) {--%>
<%--                                var img = document.getElementById("img");--%>
<%--                                img.src = e.target.result;--%>
<%--                            }--%>
<%--                            reader.readAsDataURL(file);--%>
<%--                            img.style.display = "inline";--%>
<%--                        }--%>
<%--                    </script>--%>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>邮箱：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${user.email}" name="ename"
                           placeholder="请输入邮箱"/>
                    <span></span>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>手机号码：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${user.mphone}" name="ename"
                           placeholder="请输入手机号码"/>
                    <span></span>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="note" style="height:120px;" value="${user.description}"></textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                    <button type="button" style="width: 110px;" class="button bg-green"
                            onclick="javascript:history.back(-1)"> 返回
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script>
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/employee/user?method=upload' //上传接口
            ,ext: 'jpg|png|gif|bmp|jpeg'
            ,done: function(res){
                if(res.code ==='1'){
                    alert(res.msg);
                }
                $('#img').prop('src',res.src);
                //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
</script>