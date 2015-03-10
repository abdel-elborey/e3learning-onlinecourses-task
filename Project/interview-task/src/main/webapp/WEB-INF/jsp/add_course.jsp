<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Course</title>
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
			<td valign="top">
				<jsp:include page="accordion.jsp"></jsp:include>
			</td>
			<td width="80%">
				<div class="formStyle">
				<h2><fmt:message key="addcourse.entercoursedetails" /></h2> 
				<form:form method="POST"
					commandName="course" id="courseForm">
					<table>
						<tr>
							<td><fmt:message key="addcourse.coursetitle" /><font style="color: #ff0000;">*</font></td> 
							<td><form:input path="title" /></td>
							<td><form:errors path="title" cssStyle="color: #ff0000;" /></td>
						</tr>
						
						<tr>
							<td><fmt:message key="addcourse.coursedescription" /></td>
							<td><form:textarea path="description" rows="8" cols="25" /></td>
							<td colspan="2">&nbsp;</td>
						</tr>

						<tr>
							<td><input type="submit" name="submit" value='<fmt:message key="addcourse.createcourse" />'></td>
						</tr>

					</table>
				</form:form>
				</div>
			</td>
		</tr>
	</table>
	
	
</body>
</html>
