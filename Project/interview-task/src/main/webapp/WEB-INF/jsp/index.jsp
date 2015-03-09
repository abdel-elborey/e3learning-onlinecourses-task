<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to MyEducation Online training provider</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td><table>
					<tr />
					<tr />
					<tr>
						<td>
							<div id="accordion">
								<h2>Accounts</h2>
								<div>
									<h3><a href="accounts"> Create New Account </a></h3>
									<h3><a href="searchAccounts"> Search Accounts </a></h3>
								</div>
								<h2>Courses</h2>
								<div>
									<h3><a href="courses"> Create New Course </a></h3>
								</div>
								
							</div>
						</td>
					</tr>
				</table></td>
			<td width="70%">
				<h2>Welcome to MyEducation Online training provider</h2>
			</td>
		</tr>
	</table>

</body>
</html>
