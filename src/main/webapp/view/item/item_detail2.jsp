<%@page import="javax.servlet.jsp.tagext.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.itemDetailDiv{
	width: 50%;
	margin: 0 auto;
}
.itemDetailDiv > div{
	margin-bottom: 1rem;
}
.detail1{
	overflow: auto;
	height: 25rem;
}

.detail1 > div {
	width: 50%;
	float: left;
}

.detail1 > div:first-child{
	text-align: center;
}

.detail1 img{
	width: 70%;
}

.detail1 > div:last-child > div {
	margin-bottom: 1.4rem;
}

.btn{
	font-size: 1rem;
}
</style>
</head>
<body>
<div class="itemDetailDiv">
	<div class="detail1">
		<div>
			<c:forEach items="${item.imgList }" var="imgDTO">
				<c:if test="${imgDTO.isMain eq 'Y' }">
					<img src="images/${imgDTO.attachedFileName }">
				</c:if>
			</c:forEach>
		</div>
		<div>
			<div style="padding-top: 1rem;">${itemDetail.itemName }</div>
			<div>${itemDetail.itemPrice }</div>
			<div><input type="number" class="myInput"></div>
			<div>총가격 : 10000원</div>
			<div>
				<input type="button" class="btn btn-sm" value="바로구매">
				<input type="button" class="btn btn-sm" value="장바구니">
			</div>
		</div>
	</div>
	<div class="detail2">상품소개</div>
	<div class="detail3">
		<c:forEach items="${item.imgList }" var="imgDTO">
			<c:if test="${imgDTO.isMain eq 'N' }">
				<img src="images/${imgDTO.attachedFileName }">
			</c:if>
		</c:forEach>
	</div>
</div>
</body>
</html>