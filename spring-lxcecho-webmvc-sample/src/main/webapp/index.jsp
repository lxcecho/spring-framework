<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="sayHello">sayHello</a>
<a href="/hello-servlet">helloServlet</a>

<br>
<br>

<a href="testSessionAttributes"> Test SessionAttributes</a>
<br>
<br>
<a href="testMap"> Test Map</a>
<br>
<br>
<a href="testModelAndView"> Test ModelAndView</a>
<br>
<br>
<a href="testServletAPI">testServletAPI</a>
<br>
<br>
<form action="testPojo">
    username:<label>
    <input type="text" name="username">
</label><br>
    password:<label>
    <input type="password" name="password">
</label><br>
    email:<label>
    <input type="text" name="email">
</label><br>
    age:<label>
    <input type="text" name="age">
</label><br>
    <!-- 在 form 表单中使用实体类的属性名作为 <input> 标签的 name 值（可使用级联属性） -->
    province:<label>
    <input type="text" name="address.province">
</label><br>
    city:<label>
    <input type="text" name="address.city">
</label><br>
    <input type="submit" value="submit">
</form>
<br>
<br>
<a href="testCookieValue">testCookieValue</a>
<br>
<br>
<a href="testRequestParam?username=zhangsan">testRequestParam</a>
<br>
<br>
<a href="testRequestHeader">testRequestHeader</a>
<br>
<br>
<!-- DELETE 测试 -->
<form action="testRestDelete/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="TestRest DELETE">
</form>
<br>
<br>
<!-- PUT 测试 -->
<form action="testRestPut/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="TestRest PUT">
</form>
<br>
<br>
<!-- POST 测试 -->
<form action="testRestPost/9527" method="post">
    <input type="submit" value="TestRest POST">
</form>
<br>
<br>
<!-- GET 测试 -->
<a href="testRest/1">Test Rest Get</a>
<br>
<br>
<a href="testPathVariable/666">testPathVariable...</a>
<br>
<a href="testAntPath/test/abc">testAntPath</a>
<br>
<a href="testParams?username=zhangsan&age=15">testParams</a>
<br>
<!-- 表单形式：POST 可以访问，GET 不能访问 -->
<form action="testMethod" method="post">
    <input type="submit" value="testMethod">
</form>
<br>
<!-- GET 方式提交可以正常访问，POST 不能访问 -->
<a href="testMethod">testMethod</a>
<br>
<a href="testRquestMapping">testRequestMapping...</a>
<br>
<a href="helloworld">Hello World.</a>
</body>
</html>