<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tic-Tac-Toe</title>
</head>
<body>
<%@ include file = "header.html" %>
<h1>Hello Tic-Tac-Toe!</h1>
	<form action="<c:url value='TicTacToeServlet'/>" method="get">
		<table>
			<c:forEach items="${game.grid}"var="square" varStatus="stat">
				<tr>
				<c:choose>
				
				<c:when test="${empty square}">
				
					<td>
						<button type="submit" name="loc" class="button_tile"
							value="${stat.index}"></button>
					</td>
				
				</c:when>
				<c:when test="${square.mark == 'X' }">
					<td class="X"></td>
				
				</c:when>
				<c:when test="${square.mark == 'O' }">
					<td class="O"></td>
				</c:when>
				</c:choose>
				
				<c:if test="${stat.count % 3 == 0 }">
					</tr>
			</c:forEach>
		
		</table>
	
	</form>
<%@ include file = "footer.html" %>
</body>
</html>