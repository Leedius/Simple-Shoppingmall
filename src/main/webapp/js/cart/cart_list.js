
//상품 수량 변경(방법1)
function quantityUpdate(cartCode, selectedTag){
	//수량
	//querySelectorAll : quantityInput아이디를 가진 것들 다들고온다.
	//하지만 가져온 데이터들을 특정을 할 수 없다.
	//잘못된 코드
	//const quantityInput = document.querySelectorAll('#quantityInput');
	//selectedTag : 내가 클릭한 수정 버튼
	const itemQuantity = selectedTag.previousElementSibling.value;
	
	location.href = `artItemQuantityUpdate.ct?cartCode=${cartCode}&itemQuantity=${itemQuantity}`;
}

//상품 수량 변경(방법2)
function quantityUpdate1(){
	const formTag = document.querySelectorAll('#qauntityUpdate')
		
	//가격 데이터, 수량 데이터
	const itemPrice = parseInt(document.querySelector('#itemPrice').value);
	const itemQuantity = parseInt(document.querySelector('#quantityInput').value);
	let totalInput = parseInt(document.querySelector('#total').value);
	const result = document.querySelector('#result');
	
	//총가격
	const totalPrice = itemPrice * itemQuantity;
	
	//innerText : 선택한 태그 안의 내용을 가져옴.
	result.innerText = itemPrice * itemQuantity +" 원";
	
	totalInput = totalPrice;

	document.querySelector('#total').value = totalPrice;
	
	alert(itemPrice);
	alert(itemQuantity);
	alert(totalPrice);
	
	formTag.submit();
}

//카트 품목 삭제
function deleteCart(cartCode){
	//한번 확인하기 위해confirm문 사용
	const result = confirm('정말로 삭제 하시겠습니까?');
	//result가 true면 삭제할 cartCode의 데이터를 가지고 컨트롤러로 이동
	if(result) {
		location.href = 'deleteCartItem.ct?cartCode=' + cartCode;
		
		//주의! 엔터옆의 ' 이게 아니라 숫자 1의 왼쪽의 `을 사용해야함
		//location.href = `deleteCartItem.ct?cartCode=${cartCode}`;
	}
	//false일시 다시 카트리스트로 이동
	else{
		location.href = 'cartList.ct';
	}
}

//선택구매
function goBuy(){
	const cartCodes = getCartCodes();
	location.href=`selectRegBuy.bu?cartCodes=${cartCodes}`;	//대신 백팁사용: ``
}

function getCartCodes(){
	const checkedCnt = document.querySelectorAll('.chk:Checked').length;	
	
	//선택한 상품이 없을 때
	if(checkedCnt == 0){
		alert('선택한 상품이 없습니다.');		
		return ;
	}
	//선택된 체크박스들	있을 때
	else {
			//체크한 상품의 CART_CODE 값들 가져오기
			//1. 체크된 체크박스 다 선택하기.
			const checkedBoxes = document.querySelectorAll('.chk:checked');
			//CART_CODE들을 저장할 변수 생성
			let cartCodes = ''; 
			for(const checkBox of checkedBoxes){ 
				//checkBox의 data에 저장된 값을 가져옴
				const cartCode = checkBox.dataset.cartcode;
				//반복문으로 카트코드를 계속 뽑으면 나오는 카트코드들을 문자열로 연결한다
				//예)CART_001,CART_002,CART_003,...
				cartCodes = cartCodes + cartCode +',';
			}
			//예)'hello,' -> ,를 자를때 (0,6(글자의길이)-1)
			cartCodes =cartCodes.substr(0,cartCodes.length-1);
			//document.querySelector('#cartCode').value 
			
			return cartCodes;
}
}

//선택삭제 클릭
function goDelete(){
	const checkedCnt = document.querySelectorAll('.chk:Checked').length;	
	
	//선택한 상품이 없을 때
	if(checkedCnt == 0){
		alert('선택한 상품이 없습니다.');		
		return ;
	}
	//선택된 체크박스들	있을 때
	else {
		const result = confirm('정말로 삭제 하시겠습니까?');
		
		if(result){
			const cartCodes = getCartCodes();
			location.href=`selectdelete.ct?cartCodes=${cartCodes}`;	//대신 백팁사용: ``
		}
		else{
			return;
		}	
	}	
}



//이벤트
//1. 체크박스 전체 선택, 전체 해제 이벤트
//1-1. 제목줄에 있는 체크박스를 선택
const allCheck = document.querySelector('#allChk');

//1-2. 체크박스에 클릭 시 실행되는 이벤트 추가
allCheck.addEventListener('click', function(){
	//제목 줄 체크박스의 체크 여부
	const isChecked = allCheck.checked;
	
	//체크 됐을때
	if(isChecked){
		//장바구니 목록에 있는 모든 체크박스를 선택
		const checkboxes = document.querySelectorAll('.chk');
		
		//체크 박스에 체크속성주기
		//예시 checkbox.checked = true;
		
		//가져온 체크박스의 개수만큼 반복.
		for(const checkbox of checkboxes){
			checkbox.checked = true;
		}
	}
	//체크 해제 됐을때 
	else{
		//장바구니 목록에 있는 모든 체크박스를 선택
		const checkboxes = document.querySelectorAll('.chk');
		//가져온 체크박스의 개수만큼 반복.
		for(const checkbox of checkboxes){
			checkbox.checked = false;
		}
	}
});

//2. 내용부의 체크박스에 따라 제목줄 체크박스 체크 여부 변경
//2-1. 내용부에 있는 체크박스들 선택
const checkboxes = document.querySelectorAll('.chk');

//2-2. 모든 체크박스 각각의 이벤트 추가
for(const checkbox of checkboxes){
	checkbox.addEventListener('click', function(){
		//전체체크박스 개수
		const totalCnt = checkboxes.length;
		//체크가 된 체크박스의 개수
		const checkedCnt = document.querySelectorAll('.chk:checked').length;
		
		if(totalCnt == checkedCnt){
			document.querySelector('#allChk').checked = true;
		}
		else{
			document.querySelector('#allChk').checked = false;			
		}
	});
}
