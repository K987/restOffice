<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Incomes</title>
</head>
<body>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style/page.css" />
	<title>Bevételek</title>
</body>
<h1>Bevételek listája</h1>

<c:choose>
	<c:when test="${requestScope.incomes.size() eq  0}">
		<h2>kiadás lista üres</h2>
		<a href="IncomeList">vissza</a>
	</c:when>
	<c:otherwise>

		<table class=incomeTable>
			<thead>
				<tr>
					<th>sorszám</th>
					<th>partner</th>
					<th>kelt</th>
					<th>leírás</th>
					<th>bruttó összeg</th>
					<th>bevétel típus</th>
					<th>fizetés módja</th>
					<th>fizetési határidő</th>
					<th>fizetve</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.incomes}" var="income">
					<tr>
						<td><c:out value="${income.docId }"></c:out></td>
						<td><c:out value="${income.partner }"></c:out></td>
						<fmt:formatDate var="regDate" value="${income.registered.time}"
							type="date" dateStyle="short" />
						<td><c:out value="${regDate}" /></td>
						<td><c:out value="${income.description}" /></td>
						<fmt:formatNumber var="total" value="${income.grossTotal}"
							type="currency" />
						<td><c:out value="${total}" /></td>
						<td><c:out value="${income.incomeType}" /></td>
						<td><c:out value="${income.payMethod.description}" /></td>
						<fmt:formatDate var="expDate" value="${income.expiry.time}"
							type="date" dateStyle="short" />
						<td
							class="${income.expiry.time < requestScope.today.time ?  'expiredDate' : 'valid' }"><c:out
								value="${expDate}" /></td>
						<fmt:formatDate var="payDate" value="${income.payed.time}"
							type="date" dateStyle="short" />
						<td><c:out value="${payDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
	</c:otherwise>
</c:choose>
</html>