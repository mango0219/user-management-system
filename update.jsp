<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        window.onload = function () {
            var back = document.getElementById("back");
            back.onclick = function (event) {
                location.href="${pageContext.request.contextPath}/findUserByPageServlet";
            }

            var sub = document.getElementById("sub");
            sub.onclick = function (event) {
                var name = document.getElementById("name").value;
                var  rname = /[\u4E00-\u9FA5\uF900-\uFA2D]{2,10}$/;
                if (rname.test(name)){
                    //  用户名格式正确
                    var age = document.getElementById("age").value;
                    var rage = /^[^0]\d{0,1}$/;
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
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <input name="id" value="${requestScope.user.id}" type="hidden">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" value="${requestScope.user.name}" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${'男'.equals(requestScope.user.gender)}">
                <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女"  />女
            </c:if>
            <c:if test="${'女'.equals(requestScope.user.gender)}">
                <input type="radio" name="gender" value="男"  />男
                <input type="radio" name="gender" value="女" checked />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${requestScope.user.age}" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" id="address" class="form-control" >
                <c:if test="${'陕西'.equals(requestScope.user.address)}">
                    <option value="陕西" selected>陕西</option>
                    <option value="北京">北京</option>
                    <option value="深圳">深圳</option>
                    <option value="山东">山东</option>
                    <option value="上海">上海</option>
                </c:if>
                <c:if test="${'北京'.equals(requestScope.user.address)}">
                    <option value="陕西">陕西</option>
                    <option value="北京" selected>北京</option>
                    <option value="深圳">深圳</option>
                    <option value="山东">山东</option>
                    <option value="上海">上海</option>
                </c:if>
                <c:if test="${'深圳'.equals(requestScope.user.address)}">
                    <option value="陕西" >陕西</option>
                    <option value="北京">北京</option>
                    <option value="深圳" selected>深圳</option>
                    <option value="山东">山东</option>
                    <option value="上海">上海</option>
                </c:if>
                <c:if test="${'山东'.equals(requestScope.user.address)}">
                    <option value="陕西" >陕西</option>
                    <option value="北京">北京</option>
                    <option value="深圳">深圳</option>
                    <option value="山东" selected>山东</option>
                    <option value="上海">上海</option>
                </c:if>
                <c:if test="${'上海'.equals(requestScope.user.address)}">
                    <option value="陕西" >陕西</option>
                    <option value="北京">北京</option>
                    <option value="深圳">深圳</option>
                    <option value="山东">山东</option>
                    <option value="上海" selected>上海</option>
                </c:if>

            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" class="form-control" name="qq" value="${requestScope.user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" value="${requestScope.user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" id="sub"/>
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" id="back"/>
        </div>
    </form>
</div>
</body>
</html>