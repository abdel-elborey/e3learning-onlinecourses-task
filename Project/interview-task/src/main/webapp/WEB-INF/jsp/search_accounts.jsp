<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="myApp">
<head lang="en">
<meta charset="utf-8">
<title>Search Accounts</title>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/angular.min.js"></script>
<script src="js/ng-grid.debug.js"></script>

<link rel="stylesheet" type="text/css" href="css/ng-grid.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />

<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>

<script type="text/javascript">
	var app = angular.module('myApp', [ 'ngGrid' ]);

	app
			.controller(
					'MyController',
					function($scope, $http) {
						$scope.showCourseDetails = false;
						$scope.loadUserDetails = function(userId) {
							$http({
								method : 'GET',
								url : 'getMyCourses/' + userId
							})
									.success(
											function(dataBasket, status,
													headers, config) {
												$scope.myCourses = dataBasket;
											})
									.error(
											function(data, status, headers,
													config) {
												// called asynchronously if an error occurs show here
												alert("error getting mycourses "
														+ data
														+ ":"
														+ status
														+ ":" + headers);
											});
							$http({
								method : 'GET',
								url : 'getEligibleForAccount/' + userId
							})
									.success(
											function(dataBasket, status,
													headers, config) {
												$scope.eligibleCourses = dataBasket;
												if ($scope.eligibleCourses.length > 0)
													$scope.selectedCourse = dataBasket[0].id;
											})
									.error(
											function(data, status, headers,
													config) {
												// called asynchronously if an error occurs show here
												alert("error getting eligible for courses "
														+ data
														+ ":"
														+ status
														+ ":" + headers);
											});
						}
						$scope.onMainGridRowSelect = function(row) {
							var selectedId = $scope.myData[row.rowIndex].id;
							$scope.userId = selectedId;
							$scope.loadUserDetails($scope.userId);
							$scope.showCourseDetails = true;
							//alert($scope.myData[row.rowIndex].id);

						};
						$scope.populateGridData = function() {
							$scope.showCourseDetails = false;
							var searchFirstName = angular.element(
									document.querySelector('#firstName')).val();
							var searchLastName = angular.element(
									document.querySelector('#lastName')).val();

							var dataObj = {
								firstName : searchFirstName,
								lastName : searchLastName
							};

							//var data = escape(angular.toJson({'firstName': searchFirstName , 'lastName': searchLastName}));

							$http
									.post('searchAccounts', dataObj)
									.success(
											function(dataBasket, status,
													headers, config) {
												$scope.myData = dataBasket;

											})
									.error(
											function(data, status, headers,
													config) {
												// called asynchronously if an error occurs show here
												alert("error searching for users error details:  "
														+ data);
											});
						};

						$scope.enrolUserInCourse = function() {
							if ($scope.eligibleCourses.length > 0) {
								var postDataObj = {
									accountId : $scope.userId,
									courseId : $scope.selectedCourse
								};

								$http
										.post('enrollUserInCourse', postDataObj)
										.success(
												function(dataBasket, status,
														headers, config) {
													$scope
															.loadUserDetails($scope.userId);

												}).error(
												function(data, status, headers,
														config) {
													// called asynchronously if an error occurs show here
													alert("error " + data);
												});
							} else {
								alert('There are no courses to enroll in for that user');
							}
						};

						$scope.filterOptions = {
							filterText : ''
						};

						$scope.gridOptions = {
							data : 'myData',
							showGroupPanel : true,
							filterOptions : $scope.filterOptions,
							showColumnMenu : true,
							showFilter : true,
							selectedItems : $scope.mySelections,
							multiSelect : false,
							rowHeight : 22,
							columnDefs : [ {
								field : 'firstName',
								displayName : 'First Name'
							}, {
								field : 'lastName',
								displayName : 'Last Name'
							}, {
								field : 'email',
								displayName : 'Email'
							}, {
								field : 'status',
								displayName : 'Status'
							} ],
							rowTemplate : '<div ng-click="onMainGridRowSelect(row)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>'
						};
						$scope.coursesGridOptions = {
							data : 'myCourses',
							multiSelect : false,
							rowHeight : 22,
							columnDefs : [ {
								field : 'course.title',
								displayName : 'Course Title'
							}, {
								field : 'startDate',
								displayName : 'Start Date'
							} ]
						};

					});
</script>
</head>

<body data-ng-controller="MyController" style="overflow: auto;">
	<fmt:bundle basename="messages"></fmt:bundle>
	<table>
		<tr>
			<td valign="top"><jsp:include page="accordion.jsp"></jsp:include>
			</td>
			<td width="85%">
				<div class='tab'>

					<div class="searchBoxStyle">
						<b><fmt:message key="search.firstName" /></b> <input type="text"
							id="firstName" data-ng-model="firstName" /> <b><fmt:message
								key="search.lastName" /></b> <input type="text" id="lastName"
							data-ng-model="lastName" /> <input type="button"
							ng-click="populateGridData();" value="search" /> <br>

					</div>

					<div class="filler"></div>
					<div class="gridModelStyle" data-ng-grid="gridOptions"></div>
					<div class="filler"></div>
					<div class="gridModelStyle" id="courseDetails"
						ng-show="showCourseDetails">
						<table>
							<tr>
								<td>
									<table>
										<tr>
											<td>
												<h2>
													<fmt:message key="search.currentcourses" />
												</h2>
											</td> 
										</tr>
										<tr>
											<td>
												<div class="smallGridModelStyle"
													data-ng-grid="coursesGridOptions"></div>
											</td>
										</tr>
									</table>
								</td>
								<td width="50%" style="padding: 20px; "> 
									<h2>
										<fmt:message key="search.selectcoursestoenroll" />
									</h2>
								</td>
								<td  ><select ng-model="selectedCourse"
									ng-options="course.id as course.title for course in eligibleCourses">
								</select></td>
								<td  ><input type="button"
									value='<fmt:message key="search.enroll" />'
									data-ng-click="enrolUserInCourse()" data-ng-disabled="eligibleCourses.length == 0" /></td>
						</table>

					</div>

				</div>
			</td>
		</tr>
	</table>


</body>

</html>