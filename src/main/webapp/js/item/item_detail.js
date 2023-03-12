

//var a1;

//es6 위에것 말고 아래것을 쓰는것을 추천
//const는 선언하면 바로 값을 초기화 해줘야됨. 그리고 값 변경이 안됨
//const a2 = 0;		
//let a3;

function sumPrice() {
	const result = document.querySelector('#result');
	
	//가격 데이터, 수량 데이터
	const itemPrice = parseInt(document.querySelector('#itemPrice').value);
	const itemQuantity = parseInt(document.querySelector('#itemQuantity').value);
	
	//innerText : 선택한 태그 안의 내용을 가져옴.
	result.innerText = itemPrice * itemQuantity +" 원";
}

//이미지를 클릭 할때마다 실행되는 이벤트
//
const imgTag = document.querySelector('#testImg');
imgTag.addEventListener('click', function (){
	alert(1);
});

//장바구니 버튼 클릭 시 실행
//미 로그인 시 로그인하라는 alert을 띄우고
//로그인 된 상태라면 로그인하러 이동
function goCartList(memId) {
	if(memId != ''){
		//form태그 선택
		const formTag = document.querySelector('#regCartForm');
		//submit 실행
		const result = confirm('장바구니 페이지로 이동할까요?');
		if(result){
			formTag.submit();
		}
	}
	else {
		const result = confirm('로그인 후 사용하세요.\n로그인 페이지로 이동할까요?');
		
		if(result) {
			location.href = 'loginForm.me';
		}
	}
}

const itemQuantityInput = document.querySelector('#itemQuantity');
const hiddenItemQuantityInputs = document.querySelectorAll('input[name="itemQuantity"]');
	itemQuantityInput.addEventListener('input', () => {
	const itemQuantity = parseInt(itemQuantityInput.value);
	if (itemQuantity < 1) {
	itemQuantityInput.value = 1;
	}
	hiddenItemQuantityInputs.forEach(input => input.value = itemQuantity);
	sumPrice();
});

function goBuyList(memId) {
	if(memId != ''){
		//submit 실행
		alert('결제페이지로 이동합니다.')
		if(result){
			formTag.submit();
		}
	}
	else {
		const result = confirm('로그인 후 사용하세요.\n로그인 페이지로 이동할까요?');
		
		if(result) {
			location.href = 'loginForm.me';
		}
	}
}
