<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tic-Tac-Toe</title>
<link rel="stylesheet" href="<c:url value="css/styles.css" />" />

</head>
<body>
<%@ include file = "header.jsp" %>
<div style="text-align:center">
<h1>Hello Tic-Tac-Toe!</h1>
		<c:if test="${game.winner != null }"> 
			<h1>Congratulations Player ${game.winner }</h1>			
		</c:if>
		
		<!-- Temporary design. Using as a base to test functionality. -->
			<form action="<c:url value='tictactoe'/>" method="get">
		<table>
		<tr>
			<c:forEach items= "${game.board}" var="square" varStatus="stat">
				
				<c:choose>
				
				<c:when test="${empty square}">
				
					<td>
						<button <c:if test="${game.winner != null }">value="disabled='disabled'"</c:if> type="submit" name="loc" class="button_tile"
							value="${stat.index}">${stat.count}</button>
					</td>
				
				</c:when>
				<c:when test="${square.mark == 'X' }">
					<td class="X">X</td>
				
				</c:when>
				<c:when test="${square.mark == 'O' }">
					<td class="O">O</td>
				</c:when>
				</c:choose>
				
				<c:if test="${stat.count % 3 == 0 }">
					</tr>
					<tr>
					</c:if>
			</c:forEach>
		
		
		</table>
		<button type="submit" name="newGame">New Game</button>
	</form>
	</div>
<%@ include file = "footer.jsp" %>
</body>
</html>