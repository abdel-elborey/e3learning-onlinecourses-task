<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to MyEducation Online training provider</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
</head>
<body>
<fmt:bundle basename="messages"></fmt:bundle>
	<table>
		<tr>
			<td valign="top"><jsp:include page="accordion.jsp"></jsp:include></td>
			<td width="80%">
				<div class="formStyle">
					<center><h2><fmt:message key="index.welcome" /></h2></center><br>
					<h4> <fmt:message key="index.selectactivity" /> </h4><br>
					<p>${message}</p>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>
