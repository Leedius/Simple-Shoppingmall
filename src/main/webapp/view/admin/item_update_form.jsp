<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	margin-top: 3rem;
	text-align: center;	
}

.item_input_wrap{
	margin: 0 auto;
	width: 50%;
}

.item_input_wrap .myInput{
	margin-bottom: 1rem;
}
</style>
</head>
<body>
<div>
	<div class="page_title">
		<h2>ITEM UPDATE.</h2>
	</div>
	<form action="itemUpdate.ad" method="post">
		<div class="item_input_wrap">
			<div>
				ITEM NAME.
			</div>
			<div>
				<input class="myInput" type="text" name="itemName" value="${itemDetail.itemName }">
			</div>
			<div>
				ITEM CATEGORY.		
			</div>
			<div>
				<select name="cateCode" class="myInput">
					<c:forEach items="${itemCategory }" var="item">
						<option value="${item.cateCode }">${item.cateName }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				ITEM PRICE.
			</div>
			<div>
				<input class="myInput" type="number" name="itemPrice" value="${itemDetail.itemPrice }">
			</div>
			<div>
				ITEM INTRO.
			</div>
			<div>
				<textarea class="myTextArea" name="itemIntro" >${itemDetail.itemIntro }</textarea>
			</div>
			<div>
				<input type="file" name="fileName">
			</div>
			<div class="submit_btn">
			<input class="btn btn-full" type="submit" value="RESISTE">
		</div>
		</div>
	</form>
</div>
</body>
</html>