<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="hun.restoffice.ejbservice.domain.CostCenterStub"%>
<%@ page import="hun.restoffice.ejbservice.domain.ExpenseTypeStub"%>
<%@ page import="hun.restoffice.remoteClient.domain.PaymentMethodStub"%>
<%@ page import="hun.restoffice.ejbservice.domain.ExpenseStub"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>Kiadások</title>
</head>
<body>
	<h1>Kiadások listája</h1>
	<br />
	<a href="ExpenseEdit?docId=-1">új kiadás rözítése</a>
	<br />
	<br />
	<c:choose>
		<c:when test="${requestScope.expenses.size() eq  0}">
			<h2>kiadás lista üres</h2>
			<a href="ExpenseList">vissza</a>
		</c:when>
		<c:otherwise>
			<table class=expenseTable>
				<thead>
					<tr>
						<th>sorszám</th>
						<th>kibocsátó</th>
						<th>kelt</th>
						<th>leírás</th>
						<th>bruttó összeg</th>
						<th>költséghely</th>
						<th>költségnem</th>
						<th>fizetés módja</th>
						<th>fizetési határidő</th>
						<th>fizetve</th>
						<th>módosít</th>
						<th>töröl</th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<form method="post" action="ExpenseList">
							<td>-</td>
							<!-- partners -->
							<td><select name="partner" id="partner">
									<option value="-1">-</option>
									<c:forEach items="${requestScope.partners}" var="partner">
										<option value="${partner.id}">${partner.name}</option>
									</c:forEach>
							</select></td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<!-- costCenter -->
							<td><select name="costCenter" id="costCenter">
									<option value="-1">-</option>
									<c:forEach items="${requestScope.costCenters }"
										var="costCenter">
										<option value="${costCenter.id}">${costCenter.name}</option>
									</c:forEach>
							</select></td>
							<!-- costType -->
							<td><select name="costType" value="costType">
									<option value="-1">-</option>
									<c:forEach items="${requestScope.costTypes }" var="costType">
										<option value="${costType.id}">${costType.name}</option>
									</c:forEach>
							</select></td>
							<!-- payMethod -->
							<td><select name="paymentMethod" value="paymentMethod">
									<option value="-1">-</option>
									<c:set var="paymentMethods"
										value="<%=PaymentMethodStub.values()%>" />
									<c:forEach items="${paymentMethods}" var="paymentMethod">
										<option value="${paymentMethod.ordinal()}">${paymentMethod.description}</option>
									</c:forEach>
							</select></td>
							<td>-</td>
							<!-- payed -->
							<td><select name="isPayed" id="isPayed">
									<option value="-1">-</option>
									<option value="0">nincs fizetve</option>
									<option value="1">fizetve</option>
							</select></td>

							<td><input type="submit" value="Szűr"/></td>
						</form>
						<form method="get" action="ExpenseList">
							<td><input type="submit" value="szűrés törlése" /></td>
						</form>
					</tr>
					<c:forEach items="${requestScope.expenses}" var="expense">
						<tr>
							<td><c:out value="${expense.docId}" /></td>
							<td><c:out value="${expense.issuer.name}" /></td>
							<fmt:formatDate var="regDate" value="${expense.registered.time}"
								type="date" dateStyle="short" />
							<td><c:out value="${regDate}" /></td>
							<td><c:out value="${expense.description}" /></td>
							<fmt:formatNumber var="total" value="${expense.grossTotal}"
								type="currency" />
							<td><c:out value="${total}" /></td>
							<td><c:out value="${expense.costCenter}" /></td>
							<td><c:out value="${expense.costType}" /></td>
							<td><c:out value="${expense.payMethod.description}" /></td>
							<fmt:formatDate var="expDate" value="${expense.expiry.time}"
								type="date" dateStyle="short" />
							<td
								class="${expense.expiry.time < requestScope.today.time ?  'expiredDate' : 'valid' }"><c:out
									value="${expDate}" /></td>
							<fmt:formatDate var="payDate" value="${expense.payed.time}"
								type="date" dateStyle="short" />
							<td><c:out value="${payDate}" /></td>
							<td><a
								href="ExpenseEdit?docId=<c:out value="${expense.docId}"/>">módosít</a></td>
							<td><a
								href="ExpenseDelete?docId=<c:out value="${expense.docId}"/>">töröl</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:otherwise>
	</c:choose>


</body>
</html>