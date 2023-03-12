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

.file_upload{
	margin-top: 4px; 
}
</style>
</head>
<body>
<!-- 파일을 업로드할경우 form태그에 반드시 enctype="multipart/form-data"를 추가한다 -->
<div>
	<div class="page_title">
		<h2>ITEM RESISTE.</h2>
	</div>
	<form action="regItem.ad" enctype="multipart/form-data" method="post">
		<div class="item_input_wrap">
			<div>
				ITEM NAME.
			</div>
			<div>
				<input class="myInput" type="text" name="itemName">
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
				<input class="myInput" type="number" name="itemPrice">
			</div>
			<div>
				ITEM INTRO.
			</div>
			<div>
				<textarea class="myTextArea" name="itemIntro"></textarea>
			</div>
			<div class="file_upload">
				MAIN IMAGE.
			</div>
			<div>
				<input class="file" type="file" name="mainImg" required>
			</div>
 			<div>
				SUB IMAGE.
			</div>
			<div>
				<input class="file" type="file" name="subImg" required>
			</div>
			<div class="submit_btn">
			<input class="btn btn-full" type="submit" value="RESISTE">
		</div>
		</div>
	</form>
</div>
</body>
</html>