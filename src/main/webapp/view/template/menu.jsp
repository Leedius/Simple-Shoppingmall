<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css" href="css/template/menu.css">
<script type="text/javascript">
function logout() {
	alert('로그아웃 되었습니다.')
}
</script>
</head>
<body>
<div class="menu_div">
	<c:choose>
		<c:when test="${empty sessionScope.loginInfo }">
			<div>
				<a href="loginForm.me">LOGIN</a>
				<a href="joinForm.me">JOIN</a>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				${sessionScope.loginInfo.memId }님 반값습니다.
				<a href="logout.me" onclick="logout();">LOGOUT</a><br>
				<a href="cartList.ct">CartList</a>
			</div>

		</c:otherwise>
	</c:choose>
	<div>
		BOOK SHOP
	</div>
	<c:choose>
		<c:when test="${sessionScope.loginInfo.isAdmin eq 'Y' }">
			<div>
				<ul class="menu_wrap">
					<li><a href="itemList.it">전체상품</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_001">IT/인터넷</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_002">소설</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_003">자기개발</a></li>
					<li><a href="regItemForm.ad">관리자메뉴</a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<ul class="menu_wrap">
					<li><a href="itemList.it">전체상품</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_001">IT/인터넷</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_002">소설</a></li>
					<li><a href="selectItemList.it?cateCode=CATE_003">자기개발</a></li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>