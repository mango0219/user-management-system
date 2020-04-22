<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteUser(id) {
            //  用户安全提示
            if (confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
            }
        }
    </script>
    <script>
        window.onload = function () {

            var checkall = document.getElementById("checkall");
            var check = document.getElementsByName("uid");
            checkall.onclick = function (event) {
                if (checkall.checked==false){
                    for (var i=0;i<check.length;i++){
                        check[i].checked=false;
                    }
                }else {
                    for (i=0;i<check.length;i++){
                        check[i].checked=true;
                    }
                }
            }

            for (var i=0;i<check.length;i++){
                check[i].onclick = function (event) {
                    checkall.checked=true;
                    for (var j=0;j<check.length;j++){
                        if (!check[j].checked){
                            checkall.checked=false;
                        }
                    }
                }
            }

            <%--var out = document.getElementById("out");--%>
            <%--out.onclick = function (event) {--%>
            <%--    if (confirm("您确定要退出？")){--%>
            <%--        location.href="${pageContext.request.contextPath}/outServlet";--%>
            <%--    }--%>
            <%--}--%>


            //  为删除选中按钮绑定单击响应事件
            var delSelected = document.getElementById("delSelected");
            delSelected.onclick = function (event) {
                if(confirm("是否确认删除选中用户？")){
                    var form = document.getElementById("form");
                    form.submit();
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;margin-top: 20px">
        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName1">姓名</label>
                <input type="text" class="form-control" name="name" id="exampleInputName1" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">籍贯</label>
                <input type="text" class="form-control" name="address" id="exampleInputEmail2">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">邮箱</label>
                <input type="text" class="form-control" name="email" id="exampleInputName2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px;margin-top: 20px">
        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:;" id="delSelected">删除选中</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/outServlet">退出</a>
    </div>

    <form action="${pageContext.request.contextPath}/delSelectServlet" method="post" id="form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="checkall"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:if test="${not empty requestScope.pb.list}">
                <c:forEach items="${requestScope.pb.list}" var="user" varStatus="s">
                    <tr>
                        <td><input type="checkbox" value="${user.id}" name="uid"></td>
                        <td>${s.count}</td>
                        <td>${user.name}</td>
                        <td>${user.gender}</td>
                        <td>${user.age}</td>
                        <td>${user.address}</td>
                        <td>${user.qq}</td>
                        <td>${user.email}</td>
                        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </form>

    <div style="text-align: center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${requestScope.pb.currentPage==1}">
                    <li class="disabled">
                </c:if>
                    <c:if test="${requestScope.pb.currentPage!=1}">
                    <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pb.currentPage-1}&rows=5&name=${requestScope.codition.get("name")[0]}&address=${requestScope.codition.get("address")[0]}&email=${requestScope.codition.get("email")[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>


                <c:forEach begin="1" end="${requestScope.pb.totalPage}" var="i">
                    <c:if test="${requestScope.pb.currentPage==i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${requestScope.codition.get("name")[0]}&address=${requestScope.codition.get("address")[0]}&email=${requestScope.codition.get("email")[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${requestScope.pb.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${requestScope.codition.get("name")[0]}&address=${requestScope.codition.get("address")[0]}&email=${requestScope.codition.get("email")[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>


                    <c:if test="${requestScope.pb.currentPage==requestScope.pb.totalPage}">
                        <li class="disabled">
                    </c:if>
                    <c:if test="${requestScope.pb.currentPage!=requestScope.pb.totalPage}">
                        <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${requestScope.pb.currentPage+1}&rows=5&name=${requestScope.codition.get("name")[0]}&address=${requestScope.codition.get("address")[0]}&email=${requestScope.codition.get("email")[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>

            </ul>
            <h4 style="text-align: center;margin-top: 0px">
                    共${requestScope.pb.totalCount}条记录，共${requestScope.pb.totalPage}页
            </h4>
        </nav>
    </div>
</div>
</body>
</html>

