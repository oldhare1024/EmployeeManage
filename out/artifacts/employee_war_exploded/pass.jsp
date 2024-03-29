<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="/employee/js/jquery.js"></script>
<%--    <script src="/employee/js/pintuer.js"></script>--%>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-key"></span> 修改密码</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="/employee/user?method=updatePwd">
            <div class="form-group">
                <div class="label">
                    <label for="sitename">原始密码：</label>
                </div>
                <div class="field">
                    <input type="password" onblur="verifyOldPwd()" class="input w50" id="password" name="mpass" size="50"
                           placeholder="请输入原始密码" data-validate="required:请输入原始密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="newpwd" name="newpass1" size="50" placeholder="请输入新密码"
                           data-validate="required:请输入新密码,length#>=5:新密码不能小于5位"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">确认新密码：</label>
                </div>
                <div class="field">
                    <input type="password" onblur="verifyNewPwd()" class="input w50" id="newpwd2" name="newpass" size="50" placeholder="请再次输入新密码"
                           data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script>
  function verifyOldPwd(){
    //alert("校验原始密码是否正确");
      $.post('/employee/user?method=verifyOldPwd',
          {password:$('#password').val()},
          function (data) {
              if (data !== '') {
                  alert(data);
              }
          }
          , 'text');
    }
  function verifyNewPwd() {
      // 判断两次输入的密码是否相同
      if ($('#newpwd').val() !== $('#newpwd2').val()) {
          // 如果不同，显示错误信息
          alert('两次输入的密码不相同！');
      }
  }
</script>