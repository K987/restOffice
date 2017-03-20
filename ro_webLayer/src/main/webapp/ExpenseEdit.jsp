<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="hun.restoffice.ejbservice.domain.CostCenterStub"%>
<%@ page import="hun.restoffice.ejbservice.domain.ExpenseTypeStub"%>
<%@ page import="hun.restoffice.remoteClient.domain.PaymentMethodStub"%>
<%@ page import="hun.restoffice.ejbservice.domain.PartnerStub"%>
<%@ page import="hun.restoffice.remoteClient.domain.DocTypeStub"%>
<%@ page import="hun.restoffice.ejbservice.domain.ExpenseStub"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>Kiadás kezelése</title>
</head>
<body>
	<h1>Kiadás kezelése</h1>
	<br />
	<br />
	<form method="POST" action="ExpenseEdit" accept-charset="UTF-8">
		<input type="hidden" name="isNew" value="${requestScope.isNew }" /> <label
			for="docId">számla szám:</label>
		<c:choose>
			<c:when test="${ requestScope.isNew eq '-1' }">
				<input type="text" name="docId" value="" />
			</c:when>
			<c:otherwise>
				<c:out value="${requestScope.expense.docId }" />
				<input type="hidden" name="docId"
					value="${requestScope.expense.docId }" />
			</c:otherwise>
		</c:choose>
		<br /> <label for="docType">dokumentum típus:</label> <select
			name=docType id="docType">
			<c:set var="docTypes" value="<%=DocTypeStub.values()%>" />
			<c:forEach items="${docTypes }" var="docType">
				<option value="${docType.ordinal()}"
					${ docType.ordinal() eq requestScope.expense.docType.ordinal() ? 'selected="selected"' : '' }>${docType.description}</option>
			</c:forEach>
		</select> <br /> <label for="partner">partner:</label> <select name=partner
			id="partner">
			<c:set var="partners" value="${requestScope.partners }" />
			<c:forEach items="${partners }" var="partner">
				<option value="${partner.id}"
					${ partner.id eq requestScope.expense.issuer.id ? 'selected="selected"' : '' }>${partner.name}</option>
			</c:forEach>
		</select> <br /> <label for="grossTotal">bruttó összeg:</label> <input
			type="number" name="grossTotal" id="grossTotal"
			value="${requestScope.expense.grossTotal }" /> <br /> <label
			for="description">megnevezés:</label>
		<textarea name="description" id="description" rows="5" cols="30">${requestScope.expense.description } </textarea>
		<br /> <label for="issueDate">kelt:</label>
		<fmt:formatDate var="regDate" value="${expense.registered.time}"
			type="date" dateStyle="short" />
		<input type="date" name="issueDate" id="issueDate" value="${regDate }" />
		<br /> <label for="payMethod">fizetési mód:</label> <select
			name=payMethod id="payMethod">
			<c:set var="payMethods" value="<%=PaymentMethodStub.values()%>" />
			<c:forEach items="${payMethods }" var="payMethod">
				<option value="${payMethod.ordinal()}"
					${ payMethod.ordinal() eq requestScope.expense.payMethod.ordinal() ? 'selected="selected"' : '' }>${payMethod.description}</option>
			</c:forEach>
		</select> <br /> <label for="expiryDate">fizetési határidő:</label>
		<fmt:formatDate var="expDate" value="${expense.expiry.time}"
			type="date" dateStyle="short" />
		<input type="date" name="expiryDate" id="expiryDate"
			value="${regDate }" /> <br /> <label for="payedDate">fizetve:</label>
		<c:choose>
			<c:when test="${ requestScope.isNew eq '-1' }">
				<input type="date" name="payedDate" id="payedDate"
					value="YYYY.MM.DD." />
			</c:when>
			<c:otherwise>
				<fmt:formatDate var="payedDate" value="${expense.payed.time}"
					type="date" dateStyle="short" />
				<input type="date" name="payedDate" id="payedDate"
					value="${payedDate }" />
			</c:otherwise>
		</c:choose>
		<br /> <label for="costCenter">költséghely:</label> <select
			name=costCenter id="costCenter">
			<c:set var="costCenters" value="${requestScope.costCenters }" />
			<c:forEach items="${costCenters }" var="costCenter">
				<option value="${costCenter.name}"
					${ costCenter.name eq requestScope.expense.costCenter? 'selected="selected"' : '' }>${costCenter.name}</option>
			</c:forEach>
		</select> <br /> <label for="costType">költség típus:</label> <select
			name=costType id="costType">
			<c:set var="expenseTypes" value="${requestScope.expenseTypes }" />
			<c:forEach items="${expenseTypes }" var="expenseType">
				<option value="${expenseType.name}"
					${ expenseType.name eq requestScope.expense.costType ? 'selected="selected"' : '' }>${expenseType.name}</option>
			</c:forEach>
		</select> <br /> <label for="accStartDate">elszámolási időszak
			kezdete:</label>
		<c:choose>
			<c:when test="${ requestScope.isNew eq '-1' }">
				<input type="date" name="accStartDate" id="accStartDate"
					value="YYYY.MM.DD." />
			</c:when>
			<c:otherwise>
				<fmt:formatDate var="accPeriodStart"
					value="${expense.accPeriodStart.time}" type="date"
					dateStyle="short" />
				<input type="date" name="accStartDate" id="accStartDate"
					value="${accPeriodStart }" />
			</c:otherwise>
		</c:choose>
		<br /> <label for="accEndDate">elszámolási időszak vége:</label>
		<c:choose>
			<c:when test="${ requestScope.isNew eq '-1' }">
				<input type="date" name="accEndDate" id="accEndDate"
					value="YYYY.MM.DD." />
			</c:when>
			<c:otherwise>
				<fmt:formatDate var="accPeriodEnd"
					value="${expense.accPeriodEnd.time}" type="date" dateStyle="short" />
				<input type="date" name="accEndDate" id="accEndDate"
					value="${accPeriodEnd }" />
			</c:otherwise>
		</c:choose>
		<br /> <input type="submit" value="küld" />
	</form>
	<a href="ExpenseList">vissza</a>
</body>
</html>