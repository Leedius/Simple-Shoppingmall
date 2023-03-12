<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css" href="css/template/template.css?v=03">
</head>
<body>
<div class="container">
	<div>
		<jsp:include page="menu.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="../#{contentPage }"></jsp:include>
	</div>
</div>
</body>
</html>