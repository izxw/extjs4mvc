<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>快速开发框架</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>

	<form action="${ctx }/login" method="post">
		<p>
			用户账号：<input type="text" name="username" value="${username }" />
		</p>

		<p>
			用户密码：<input type="password" name="password" value="${password }" />
		</p>

		<p>
			<input type="submit" value="登录" />
		</p>

	</form>

</body>
</html>

