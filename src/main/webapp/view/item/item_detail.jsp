<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.btn_wrap div {
	display: inline-flex;
}

.page_title > h2{
	text-align: center;
}

.info_wrap{
	text-align: center;
}

.info_wrap > div{
	display: inline-block;	
	vertical-align:top;
}

.item_info_wrap{
	margin: 0 auto;
	width: 30%;
	text-align: right;
}

.item_info_wrap > div{
	text-align: left;
}

.item_info_wrap > div:nth-child(odd) {
	text-decoration: underline;
	font-size: 1.7rem;
}

.item_info_wrap > div:nth-child(even) {
	margin-bottom: 4px;
}

.item_info_wrap > div:last-child {
	text-decoration: none;
}

.item_image_wrap{
	text-align: center;
	vertical-align: middle;
	font-size: 10rem;
	margin-bottom: 0;
	margin-right: 3rem;
	width: 30%
}

.item_detail_wrap{
	margin: 0 auto;
	margin-top: 5rem;
	text-align: center;
	width: 70%;
}

.item_detail_wrap > div:nth-child(1){
	margin-bottom: 1rem;
}

.btn_wrap{
	margin-top: 1rem;
	text-align: center;
}

.item_image_wrap img{
	width: 100%;
}

.item_intro{
	width: 70%;
	text-align: left;
	margin: 1rem 0;
}

.input_info{
	width: 25%;
}

.myInput{
	width: 40%;
}

.btn_wrap{
	margin-top: 2rem;
}

.purchase_btn{
	width: 40%;
	font-size: 1rem;
}

.item_btn_wrap{
	margin-top: 1rem;
}

.item_btn_wrap form > input[type="hidden"]:nth-child(1),
.item_btn_wrap form > input[type="hidden"]:nth-child(2){
	width: 0;
}
.item_btn_wrap form{
	display: inline-block;
}
.item_btn_wrap form > input:nth-child(3) {
	width: 100%;
}

</style>
</head>
<body>
<div>
	<div class="page_title">
		<h2>책 상세 정보.</h2>	
	</div>
	<!-- loginInfo의 자료형은 MemeberDTO이기 때문에 문자열로 넘겨야 해서 memId만 넘긴다. -->
	
	<div class="info_wrap">
		<div class="item_image_wrap">
			<c:forEach items="${itemDetail.imgList }" var="img" begin="0" end="0">
				<c:if test="${img.isMain eq 'Y' }">
					<div>
						<img id="testImg" src="images/${img.attachedFileName }">					
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="item_info_wrap">
			<div>
				제목
			</div>
			<div>
				${itemDetail.itemName }
			</div>
			<div>
				책 코드
			</div>
			<div>
				${itemDetail.itemCode }
			</div>
			<div>
				카테고리
			</div>	
			<div>
				${itemCategory.cateName }
			</div>
			<div>
				가격
			</div>
			<div>
				<input type="hidden" id="itemPrice" value="${itemDetail.itemPrice }">
				${itemDetail.itemPrice }
			</div>
			<div>
				등록일
			</div>	
			<div>
				${itemDetail.regDate }
			</div>
			<div>
				구입할 개수
			</div>
			<div>
				<input class="myInput" type="number" name="itemQuantity" id="itemQuantity" value="1" onchange="if(parseInt(this.value) < 1) {this.value = 1;} sumPrice();" onkeyup="if(parseInt(this.value) < 1) {this.value = 1;} sumPrice();">
			</div>
			<div>
				총 가격
			</div>
			<div id="result" > 
				${itemDetail.itemPrice } 원
			</div>
			<div class="item_btn_wrap">
				<form action="regCart.ct" method="post" id="regCartForm">
					<input type="hidden" id="itemCode" name="itemCode" value="${itemDetail.itemCode }">
					<input type="hidden" id="itemQuantity" name="itemQuantity" value="1">
					<input class="btn purchase_btn" type="button" value="바로구매" onclick="goBuyList('${sessionScope.loginInfo.memId}');">
				</form>
				<form>
					<input type="hidden" id="itemCode" name="itemCode" value="${itemDetail.itemCode }">
					<input type="hidden" id="itemQuantity" name="itemQuantity" value="1">
					<input class="btn purchase_btn" type="button" value="장바구니에 담기" onclick="goCartList('${sessionScope.loginInfo.memId}');">
				</form>
			</div>
		</div>
	</div>
	
	<div class="item_detail_wrap">
		<div>
			<h3>책 상세 정보</h3>
		</div>
		<div class="item_intro">
			<h4>상품소개.</h4>
		</div>
		<div class="item_intro">
			${itemDetail.itemIntro }
		</div>
		<div>
			<c:forEach items="${itemDetail.imgList }" var="img">
				<c:if test="${img.isMain eq 'N' }">
					<div>
						<img src="images/${img.attachedFileName }">					
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<div class="btn_wrap">
		<div>
			<input class="btn" type="button" value="목록" onclick="location.href='itemList.it'">	
		</div>
		<c:choose>
			<c:when test="${sessionScope.loginInfo.isAdmin eq 'Y' }">
				<div>
					<input class="btn" type="button" value="수정">
				</div>
				<div>
					<input class="btn" type="button" value="삭제">
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<!-- javascript 파일 로딩 -->
<!-- 저장하고 새로고침하면 로딩이 될거임 -->
<script type="text/javascript" src="js/item/item_detail.js?v=07">
</script>
</body>
</html>