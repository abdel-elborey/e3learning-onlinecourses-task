<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
<script>
	$(function() {
	     $('#accountForm').submit( function() {
	    	 if($("#statuschk").prop("checked")){
	    		 $("#accountStatus").val('ACTIVE');
	    	 }else{
	    		 $("#accountStatus").val('INACTIVE');
	    	 }

	     });
	});
	
	$(function() {
	    if($('#accountStatus').val() == 'ACTIVE'){
	    	$("#statuschk").prop("checked",true);
	    } else{
	    	$("#statuschk").prop("checked",false);
	    }
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
		
				<h2><fmt:message key="addaccount.enteraccountdetails" /></h2> <form:form method="POST"
					commandName="account" id="accountForm">
					<table>
						<tr>
							<td><fmt:message key="addaccount.firstName" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="firstName" /></td>
							<td><form:errors path="firstName" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.lastName" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="lastName" /></td>
							<td><form:errors path="lastName" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.email" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="email" /></td>
							<td><form:errors path="email" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><b><fmt:message key="addaccount.addressInformation" /></b></td>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.streetName" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="address.streetName" /></td>
							<td><form:errors path="address.streetName"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.suburb" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="address.suburb" /></td>
							<td><form:errors path="address.suburb"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.state" /><font style="color: #ff0000;">*</font></td>
							<td><form:input path="address.state" /></td>
							<td><form:errors path="address.state"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addaccount.country" /></td>
							<td><form:select path="address.country.id">
									<form:options itemLabel="name" itemValue="id"
										items="${countries}" />
								</form:select></td>
							<td><form:errors path="address.country"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><form:hidden path="status" id="accountStatus" /> <input
								type="checkbox" id="statuschk" /> <fmt:message key="addaccount.active" /></td>
							<td colspan="2">&nbsp;</td>
						</tr>

						<tr>
							<td><fmt:message key="addaccount.enrollincourses" /></td>
							<td><form:select path="training" multiple="multiple" >
									<form:options items="${availableTraining}"
										itemLabel="course.title" itemValue="course.id" />
								</form:select></td>
						</tr>

						<tr>
							<td><input type="submit" name="submit"
								value='<fmt:message key="addaccount.createAccount" />'></td>
						</tr>

					</table>
				</form:form>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
