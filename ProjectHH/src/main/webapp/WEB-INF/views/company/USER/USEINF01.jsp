<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 내 정보 보기 -->
<link href="../../../../css/mypage.css" rel="stylesheet">
</head>
<body>
	<div id="container">
			<div id="top">
				<h1>마이페이지</h1>
			</div>
			<hr>
			<div id="container">
			<div id="left">
				<!-- 구성 -->
				<a href="">마이페이지</a><br> <a onclick="">회원 수정</a><br> <a
					href="">회원 탈퇴</a><br> <a href="">구매 내역</a><br>
			</div>
				<table>
					<tr>
						<th>아이디</th>
						<th>${dto.id }</th>
					</tr>
					<tr>
						<th>이름</th>
						<th>${dto.name }</th>
					</tr>
					<tr>
						<th>주소</th>
						<th>${dto.address }</th>
					</tr>
					<tr>
						<th>전화번호</th>
						<th>${dto.tel }</th>
					</tr>
				</table>
				<div class="right">
				<button type="button" onclick="location.href='/infedit?no=${dto.id}'">수정</button>
			</div>
			</div>
	</div>
</body>
</html>