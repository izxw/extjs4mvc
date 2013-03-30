<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Ext4Mvc</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"  type="text/css" href="resources/ext-gpl/three/web.css"/>
	<link rel="stylesheet" href="resources/ext-gpl/resources/css/ext-all.css" type="text/css"></link>
	<link rel="stylesheet" href="resources/ext-gpl/resources/css/icon.css" type="text/css"></link>
	<script type="text/javascript" src="resources/ext-gpl/resources/ext-all.js"></script>
	<script type="text/javascript" src="resources/app/utils/comm.js"></script>
    <script type="text/javascript" src="resources/app/loader.js"></script>
	<script type="text/javascript" src="resources/app/app.js"></script>
	
	<style type="text/css">
	body{
		background-color:#e7eff0;
	}
	#footer{
		line-height:34px;
		text-align:center;
		vertical-align: middle;
		background-color:#e7eff0;
	}
	#logo{
		line-height:50px;
		background: url(resources/ext-gpl/images/header_bg.png) repeat-x;
	}

	</style>
  </head>
  
  <body>
  	<script type="text/javascript">
  	
  	comm.add("ctx","${ctx}/");
  	
	
  	var array=[{featureName:'userlist-create',status:'hidden',value:false},{featureName:'userlist-edit',status:'hidden',value:false},{featureName:'userlist-create',status:'disabled',value:true}];
  	 
  	comm.add("user",array);
  	 
  	</script>
  	<div id="logo"><img src="resources/ext-gpl/images/logo.png"/> </div>
  	
  	<div id="content">主体内容</div>
  	
  	<div id="footer">Copyright © 2013 四川金新石信息技术有限公司 Tel：028-66607012</div>
  	
  </body>
</html>

