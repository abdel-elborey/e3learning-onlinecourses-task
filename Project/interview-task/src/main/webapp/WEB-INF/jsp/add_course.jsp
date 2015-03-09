<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Course</title>
<script src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h2>Enter Course Details</h2>
	<form:form method="POST" commandName="course" id="courseForm">
        <table>
            <tr>
                <td>Course Title:</td>
                <td><form:input path="title" /></td>
                <td><form:errors path="title" cssStyle="color: #ff0000;"/></td>
            </tr>

            <tr>
                <td><input type="submit" name="submit" value="Create Course"></td>
            </tr>
            
        </table>
    </form:form>
	
</body>
</html>
