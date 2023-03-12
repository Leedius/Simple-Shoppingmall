<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.login_wrap{
	margin: 0 auto;
	width: 40%;
}

.login_title{
	margin-top: 3rem;
}

h2{
	text-align: center;
}
</style>
<script type="text/javascript">
function checkValue()
{
    inputForm = eval("document.loginInfo");
    if(!inputForm.memId.value)
    {
        alert("아이디를 입력하세요");    
        inputForm.memId.focus();
        return false;
    }
    if(!inputForm.memPw.value)
    {
        alert("비밀번호를 입력하세요");    
        inputForm.memPw.focus();
        return false;
    }
}
</script>
</head>
<body>
<div>
	<div class="login_wrap">
		<div class="login_title">
			<h2>L O G I N</h2>
		</div>
		<form action="login.me" method="post" onsubmit="return checkValue()">
		<div>
			ID.
		</div>
		<div>
			<input class="myInput" type="text" name="memId" placeholder="Input Your Id" required> 
		</div>
		<div>
			PW.
		</div>
		<div>
			<input class="myInput" type="password" name="memPw" placeholder="Input Your Password" required> 
		</div>
		<div class="submit_btn">
			<input class="btn btn-full" type="submit" value="l o g i n">
		</div>
		</form>
	</div>
</div>
</body>
</html>