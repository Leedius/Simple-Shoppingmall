<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.page_title{
	text-align: center;
	margin-top: 3rem;
}

.item_wrap{
}

.itemRow{
	overflow: auto;
	
}

.title_wrap{
	text-align: center;
}

.item{
	float: left;
	width: 20%;
	margin-bottom: 7rem;
}

.item > div{
	text-align: center;
}

img{
	width: 90%;
	height: 15rem;
}

img:hover {
	opacity: 0.5;
}
</style>
</head>
<body>
<div>
	<div class="page_title">
		<h2>ITEM LIST</h2>
	</div>
	<div class="item_wrap">
		<c:forEach items="${itemList }" var="item" varStatus="status">
			<c:if test="${status.index % 5 eq 0 }">
				<div class="itemRow">
			</c:if>
				<div class="item">
					<div class="item_info">
						<a href="goItemDetail.it?itemCode=${item.itemCode}"><img src="images/${item.imgList[0].attachedFileName }"></a>
					</div>
					<div class="item_info">${item.itemName }</div> 
					<div class="item_info">${item.itemPrice } 원</div> 
				</div>
			<c:if test="${status.index % 5 eq 4 }">
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>
</body>
</html>