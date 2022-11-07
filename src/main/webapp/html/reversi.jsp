<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
.game_table th {
	color: white;
	border-color: black;
}

.black_tile {
	color: black;
	border-color: black;
}

.white_tile {
	color: white;
	border-color: black;
}

.game_table {
	border: 1px solid black;
	border-collapse: collapse;
	table-layout: fixed;
}

.tile_numbers {
	color: white;
	border-color: black;
}

.button_tile {
	width: 40px;
	height: 35px;
	background-color: green;
}

.center_content {
	text-align: center;
}

ul {
	list-style-type: none;
}

table, th, td {
	 margin-left: auto;
	 margin-right: auto; 
}
</style>
<title>Reversi</title>
</head>
<body>
<%@ include file = "header.jsp" %>
	<div class="center_content">
	<h1>Reversi</h1>
	<form action="<c:url value='reversi'/>" method="get">
		<div>
			<table border="1" style="background-color: Green;" class="game_table">
				<tr>
					<th></th>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>
				</tr>
				<tr>
					<c:set var="count" value="1" scope="page" />
					<td class="tile_numbers">${count}
					</th>
					<c:forEach items="${reversi.grid}" var="space" varStatus="stat">


						<c:choose>
							<c:when test="${empty space }">
								<td>
									<button type="submit" name="loc" class="button_tile"
										value="${stat.index}"></button>
								</td>
							</c:when>
							<c:when test="${space.marker == 'BLACK'}">
								<td class="black_tile" style="text-align: center;"><img
									src="images/black.png" alt="black tile" width="30" height="30"></td>
							</c:when>
							<c:otherwise>
								<td class="white_tile" style="text-align: center;"><img
									src="images/white.png" alt="black tile" width="30" height="30"></td>
							</c:otherwise>
						</c:choose>


						<c:if test="${stat.count % 8 == 0 }">
				</tr>
				<tr>
					<c:set var="count" value="${count+1}" scope="page" />
					<c:if test="${count < 9}">
						<td class="tile_numbers">${count}</td>
					</c:if>

					</c:if>
					</c:forEach>
				<tr>
					<th></th>
					<th>a</th>
					<th>b</th>
					<th>c</th>
					<th>d</th>
					<th>e</th>
					<th>f</th>
					<th>g</th>
					<th>h</th>
				</tr>
				<tr>


					<ul>
						<li>Current Player: ${reversi.currentPlayer}
						<li>Current Score: <c:forEach items="${reversi.scoreBoard}"
								var="entry">
								  				 ${entry.key} ${entry.value} 
							   			</c:forEach>
						</li>
						<c:if test="${not reversi.playing}">
							<li style="color:red;">Game Over Winner is: ${reversi.winner}</li>
						</c:if>
					</ul>
				</tr>
			</table>
		</div>
		<button type="submit" name="quit">Quit</button>
	</form>
	</div>
	<%@ include file = "footer.jsp" %>
</body>
</html>