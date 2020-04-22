<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/4/21
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        window.onload = function () {
            var back = document.getElementById("back");
            back.onclick = function (event) {
                location.href = "${pageContext.request.contextPath}/findUserByPageServlet";
            }

            // 对表单信息格式进行判断
            var sub = document.getElementById("sub");
            sub.onclick = function (event) {
                var name = document.getElementById("name").value;
                var  rname = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,10}$/;
                if (rname.test(name)){
                    //  用户名格式正确
                    var age = document.getElementById("age").value;
                    var rage = /^[^0]\d$/;
                    if (rage.test(age)){
                        //  年龄格式正确
                        var qq = document.getElementById("qq").value;
                        var rqq = /^[^0]\d{5,10}$/;
                        if (rqq.test(qq)){
                            //  qq格式正确
                            var email = document.getElementById("email").value;
                            var rmail = /^\w{3,}(\.\w+)*@[A-z0-9]{1,}(\.[A-z]{2,5}){1,2}$/;
                            if (!rmail.test(email)){
                                // email格式错误
                                alert("请输入正确的email！");
                                event.preventDefault();
                            }
                        }else {
                            //  qq格式错误
                            alert("请输入正确的QQ");
                            event.preventDefault();
                        }
                    }else {
                        // 年龄格式错误
                        alert("请输入正确的年龄！");
                        event.preventDefault();
                    }
                }else {
                    // 用户名格式错误
                    alert("请输入正确的姓名！");
                    event.preventDefault();
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="陕西">陕西</option>
                <option value="北京">北京</option>
                <option value="深圳">深圳</option>
                <option value="山东">山东</option>
                <option value="上海">上海</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" id="sub" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" id="back" value="返回" />
        </div>
    </form>
</div>
</body>
</html>