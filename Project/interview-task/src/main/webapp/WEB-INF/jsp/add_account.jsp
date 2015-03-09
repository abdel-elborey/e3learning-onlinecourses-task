<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
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
	<table>
		<tr>
			<td><table>
					<tr>
						<td height="50">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div id="accordion">
								<h2>Accounts</h2>
								<div>
									<h3>
										<a href="accounts"> Create New Account </a>
									</h3>
									<h3>
										<a href="searchAccounts"> Search Accounts </a>
									</h3>
								</div>
								<h2>Courses</h2>
								<div>
									<h3>
										<a href="courses"> Create New Course </a>
									</h3>
								</div>

							</div>
						</td>
					</tr>
				</table></td>
			<td width="70%">
				<h2>Enter Account Details</h2> <form:form method="POST"
					commandName="account" id="accountForm">
					<table>
						<tr>
							<td>First Name:</td>
							<td><form:input path="firstName" /></td>
							<td><form:errors path="firstName" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td>Last Name:</td>
							<td><form:input path="lastName" /></td>
							<td><form:errors path="lastName" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><form:input path="email" /></td>
							<td><form:errors path="email" cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><b>Address Information</b></td>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>Street Name:</td>
							<td><form:input path="address.streetName" /></td>
							<td><form:errors path="address.streetName"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td>Suburb:</td>
							<td><form:input path="address.suburb" /></td>
							<td><form:errors path="address.suburb"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><form:input path="address.state" /></td>
							<td><form:errors path="address.state"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td>Country :</td>
							<td><form:select path="address.country.id">
									<form:options itemLabel="name" itemValue="id"
										items="${countries}" />
								</form:select></td>
							<td><form:errors path="address.country"
									cssStyle="color: #ff0000;" /></td>
						</tr>
						<tr>
							<td><form:hidden path="status" id="accountStatus" /> <input
								type="checkbox" id="statuschk" /> Active</td>
							<td colspan="2">&nbsp;</td>
						</tr>

						<tr>
							<td>Enroll in courses</td>
							<td><form:select path="training" multiple="multiple">
									<form:options items="${availableTraining}"
										itemLabel="course.title" itemValue="course.id" />
								</form:select></td>
						</tr>

						<tr>
							<td><input type="submit" name="submit"
								value="Create Account"></td>
						</tr>

					</table>
				</form:form>
			</td>
		</tr>
	</table>
</body>
</html>
