<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<div>
		<c:choose>
			<c:when test="${sessionScope.loginInfo.isAdmin eq 'Y'}">
				<script type="text/javascript">
					alert('반갑습니다.');
					location.href = 'itemList.it';
				</script>
			</c:when>
			<c:when test="${empty sessionScope.loginInfo}">
				<script type="text/javascript">
					alert('로그인 실패 아이디와 비밀번호를 확인하세요');
					location.href = 'loginForm.me';
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					alert('로그인 성공')
					location.href = 'itemList.it';
				</script>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
</html>