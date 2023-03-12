<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css" href="css/template/template.css?v=02">
<style type="text/css">
.joinDiv{
   width: 50%;
   margin: 0 auto;
}
.joinDiv > div:first-child{
   width: 50%;
   border-bottom: 3px solid #999999;
   font-size: 32px;
   font-weight: bold;
   font-style: italic;
   margin-bottom: 20px;
}
.joinDiv > div:last-child > div {
   margin-bottom: 16px;
}
.joinDiv > div:last-child > div > div:first-child{
   font-style: italic;
}
</style>
</head>
<body>
<form action="join.me" method="post">
   <div class="joinDiv">
      <div>
         JOIN MEMBER.
      </div>
      <div>
         <div>
            <div>ID.</div>
            <div>
               <input type="text" class="myInput" name="memId" required>
            </div>
         </div>
         <div>
            <div>PW.</div>
            <div>
               <input type="password" class="myInput" name="memPw" required>
            </div>
         </div>
         <div>
            <div>NAME.</div>
            <div>
               <input type="text" class="myInput" name="memName" required>
            </div>
         </div>
         <div>
            <div>GENDER.</div>
            <div>
               <input type="radio" name="gender" value="남" checked>남
               <input type="radio" name="gender" value="남">여
            </div>
         </div>
         <div>
            <div>ADDRESS.</div>
            <div>
               <div style="margin-bottom: 10px;">
                  <input type="text" id="address" class="myInput" name="addr" onclick="searchAddr()" required readonly style="width: 78.4%; vertical-align: middle;">
                  <input type="button" class="btn" value="검 색" onclick="searchAddr()" style="width: 20%; vertical-align: middle;">
               </div>
               <div>
                  <input type="text" id="detailAddress" class="myInput" name="addrDetail" required>
               </div>
            </div>
         </div>
         <div>
            <div>TELL.</div>
            <div>
               <select name="memTell" class="myInput" style="width: 30%;">
                  <option value="010">010</option>
                  <option value="010">011</option>
               </select>
               <input type="text" class="myInput" name="memTell" required style="width: 33.6%;">
               <input type="text" class="myInput" name="memTell" required style="width: 33.6%;">
            </div>
         </div>
         <div>
            <div>EMAIL.</div>
            <div>
               <input type="text" class="myInput" name="memEmail" style="width: 50%"> @
               <select name="memEmail" class="myInput" style="width: 43.5%">
                  <option value="@gmail.com">gmail.com</option>
                  <option value="@naver.com">naver.com</option>
               </select>
            </div>
         </div>
         <div>
            <input type="submit" class="btn btn-full" value="J O I N">
         </div>
      </div>
   </div>
</form>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="js/member/join.js?v=02">
</script>
</html>
