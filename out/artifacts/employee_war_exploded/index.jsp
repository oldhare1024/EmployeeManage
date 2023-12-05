<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="${user.img}" class="radius-circle rotate-hover" height="50" alt=""/>后台管理中心</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span>后台主页</a>
        &nbsp;&nbsp;
        <a class="button button-little bg-blue" href="" target="_blank"><span class="icon-refresh"></span>刷新数据</a>
        &nbsp;
        &nbsp;&nbsp;<a class="button button-little bg-red" onclick="logOut()" href="javascript:void(0)"><span
                class="icon-power-off"></span> 退出登录</a>
    </div>
    <div class="logo margin-big-right fadein-top" style="margin-left: 650px;">
        <h4>欢迎，<span
                style="font-family: 幼圆;color: #0000FF;font-size: 20px;font-weight: bold;">${user.username}</span></h4>
    </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基础数据</h2>
    <ul style="display:block">
        <li><a href="/employee/emp?method=empList" target="right"><span class="icon-magic"></span>员工管理</a></li>
        <li><a href="dept.html" target="right"><span class="icon-foursquare"></span>部门管理</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>用户管理</h2>
    <ul>
        <li><a href="/employee/pass.jsp" target="right"><span class="icon-key"></span>修改密码</a></li>
        <li><a href="/employee/personal.jsp" target="right"><span class="icon-pencil"></span>个人信息</a></li>
    </ul>
</div>
<ul class="bread">
    <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
    <li><a href="##" id="a_leader_txt">网站信息</a></li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="backIndex.html" name="right" width="100%" height="100%"></iframe>
</div>
<div class="music">
    <audio src="music/CrystalOverture.mp3" id="bgm" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>
<div style="width:300px;margin:0 auto; padding:20px 0;">
    <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=41102402000277" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
        <img src="" style="float:left;"/>
        <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">123</p></a>
</div>
</body>
</html>
<script>
    function logOut() {
        let logOut = confirm("确认退出系统吗?")
        if (logOut) {
            location.href = "user?method=logOut";
        }
    }
</script>
<script type="text/javascript">
    function zt() {
        var bgm = document.getElementById("bgm")
        bgm.pause()
    }

    function ks() {
        var bgm = document.getElementById("bgm")
        bgm.play()
    }

    function cz() {
        var bgm = document.getElementById("bgm")
        bgm.load()
    }
</script>
<script type="text/javascript">
    $(function () {
        $(".leftnav h2").click(function () {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function () {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
</script>