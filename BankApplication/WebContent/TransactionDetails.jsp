<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
				<style>
					table, tr, td{
						 border: 1px solid black;
						 width : 40%;
						 text-align: center;
					}
				</style>
</head>
<body>
					<c:forEach var="Transaction" items="${list}">
						<table>
							<tr>
							
								<td>
									<h5><c:out value="${Transaction.getAccountno()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${Transaction.getTransction()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${Transaction. getTransferbal()}"></c:out></h5>
								</td>	
								
							</tr>
						</table>
					</c:forEach>
</body>
</html>

