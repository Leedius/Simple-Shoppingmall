<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.page_wrap{
	width:100%;
	margin: 0 auto;
	margin-top:3rem;
}

.page_wrap > div{
	margin: 0 auto;
}

.page_wrap li:nth-child(1) {
	width: 3%
}
.page_wrap li:nth-child(2) {
	width: 10%
}
.page_wrap li:nth-child(3) {
	width: 20%
}
.page_wrap li:nth-child(4) {
	width: 10%
}
.page_wrap li:nth-child(5) {
	width: 20%
}
.page_wrap li:nth-child(6) {
	width: 15%
}
.page_wrap li:nth-child(7) {
	width: 15%
}

.title_wrap{
	padding: 1rem 0;
	border-top: 1px solid black;
	background-color: #d1e7f0;
}

.cart_list_wrap:last-child{
	border-bottom: 1px solid black;
}

.page_wrap ul{
	border-top: 1px solid black;
	margin: 0;
	padding: 0;
	vertical-align: middle;
}

.title_wrap ul{
	border-top: none;
	vertical-align: middle;
}

.page_wrap img{
	width: 100%;
}

.page_wrap .menu_wrap li{
	vertical-align: middle;
}

.total_price{
	border: none;
}

.bottom_button_wrap{
	padding-top: 3rem;
	text-align: center;
}

</style>
</head>
<body>
	<div class="page_wrap">
		<div class="title_wrap">
			<ul class="menu_wrap">
				<li><input type="checkbox" id="allChk" checked></li>
				<li>상품이미지</li>
				<li>상품명</li>
				<li>가격</li>
				<li>수량</li>
				<li>총가격</li>
				<li></li>
			</ul>
		</div>
		<div>
		<!-- foreach문안에 있는 id들은 중복이된다 -->
		<c:choose>
			<c:when test="${!empty cartList }">
				<c:forEach items="${cartList }" var="cart" varStatus="i">
					<div class="cart_list_wrap">
						<ul class="menu_wrap">
							<li><input data-cart-code="bbb"
								data-cartCode="${cart.cartCode }" type="checkbox" class="chk" checked
								value="${cart.itemCode }"></li>
							<c:if test="${cart.isMain eq 'Y' }">
								<li><img src="images/${cart.attachedFileName }"></li>
							</c:if>
							<li>${cart.itemName }</li>
							<li><input type="hidden" id="itemPrice" class="itemPrice"
								value="${cart.itemPrice }"> ${cart.itemPrice } 원</li>
							<li>
								<form action="cartItemQuantityUpdate.ct" method="post" id="quantityUpdate${i.index }">
									<input class="itemQuantity" name="itemQuantity"
									style="width: 2rem; text-align: center; vertical-align: middle"
									type="number"
									value="${cart.itemQuantity }" id="quantityInput"
									onload="sumPrice();"
									onchange="if(parseInt(this.value) < 1) {this.value = 1;} "
									onkeyup="if(parseInt(this.value) < 1) {this.value = 1;} ">								
									<!-- 버튼을 누르면 자바스크립트로 데이터넘기기 -->
									<%-- <button type="button" id="updateBtn" onclick="quantityUpdate('${cartCode}', this);">수정</button> --%>
									<!-- form태그를 통해서 자바스크립트 사용하기 -->
									<input type="submit" value="수정">
									<input name="cartCode" type="hidden" value="${cart.cartCode }"
									id="cartCode">
									<input type="hidden" name="totalPrice"
									id="total" value="0">
								</form>
							</li>
							<li id="result" class="result"> ${cart.totalPrice } 원</li>
							<li><input type="button" value="삭제" id="deleteCartItem"
									onclick="deleteCart('${cart.cartCode}');"></li>
						</ul>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="cart_list_wrap" style="text-align: center;">
					장바구니에 담긴 상품이 없습니다.
				</div>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="bottom_button_wrap">
			<input class="btn" type="button" value="선택구매" onclick="goBuy();">
			<!-- goBuy():자바스크립트문법이므로 자스로 가져간다. -->
			<input class="btn" type="button" value="선택삭제" onclick="goDelete()">
		</div> 
	</div>
	<script type="text/javascript" src="js/cart/cart_list.js?v=61"></script>
</body>
</html>