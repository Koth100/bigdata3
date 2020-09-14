<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>$$$회원가입$$$</title>
<style>
	body{
		font-size: 12px;
	}
</style>
<script type="text/javascript">
//코드 어시스턴스 를 깔면 컨트롤 space시 내용이 나옴
</script>
</head>
<body>
-회원가입-
  <form action="${cpath}/insert.do" method="post">
  	<table border="1">
  		<tr>
  			<td>이름</td>
  			<!-- 통일성을 위해 name도 VO와 일치시켜주면 좋다. -->
  			<td><input type="text" name="name" /></td>
  		</tr>
  		<tr>
  			<td>전화번호</td>
  			<td><input type="text" name="phone" /></td>
  		</tr>
  		<tr>
  			<td>주소</td>
  			<td><input type="text" name="addr" size="50" />
  			<input type="button" value="위도경도구하기"/></td>
  		</tr>
  		<tr>
  			<td colspan="2" align="center">
				<input type="submit" value="가입"/>
				<input type="reset" value="취소"/>  			
  			</td>
  		</tr>
  	</table>
  </form>

</body>
</html>