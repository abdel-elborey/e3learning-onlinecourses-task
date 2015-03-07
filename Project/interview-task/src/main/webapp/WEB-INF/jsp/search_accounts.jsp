<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="myApp">
<head lang="en">
<meta charset="utf-8">
<title>Search Accounts seko1</title>

<link rel="stylesheet" type="text/css" href="css/ng-grid.css" />
<script src="js/jquery.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script type="text/javascript" src="js/ng-grid.debug.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />

  <script type="text/javascript">

    var app = angular.module('myApp', ['ngGrid']);
    
    app.controller('MyController', function($scope, $http) {
		$scope.showCourseDetails = false;
 		$scope.onMainGridRowSelect = function(row){
 			var selectedId = $scope.myData[row.rowIndex].id;
 			$scope.userId = selectedId;
 			$http({
 				method : 'GET',
 				url : 'getMyCourses/' + selectedId}).
 			    success(function(dataBasket, status, headers, config) {
 			    	$scope.myCourses = dataBasket;	   			    	
 			    }).
 			    error(function(data, status, headers, config) {
 			    	// called asynchronously if an error occurs show here
 			    	alert("error getting mycourses " + data + ":" + status + ":" + headers);
 			    });
 			$http({
 				method : 'GET',
 				url : 'getEligibleForAccount/' + selectedId}).
 			    success(function(dataBasket, status, headers, config) {
 			    	$scope.eligibleCourses = dataBasket;
 			    	if($scope.eligibleCourses.length > 0)
 			    		$scope.selectedCourse = dataBasket[0].id;
 			    }).
 			    error(function(data, status, headers, config) {
 			    	// called asynchronously if an error occurs show here
 			    	alert("error getting eligible for courses " + data + ":" + status + ":" + headers);
 			    });
 			$scope.showCourseDetails = true;
 				//alert($scope.myData[row.rowIndex].id);
 			
 		};
    	$scope.populateGridData = function() {
    		$scope.showCourseDetails = false;
    		var searchFirstName = angular.element(document.querySelector('#firstName')).val();
			var searchLastName = angular.element(document.querySelector('#lastName')).val();
			
			var dataObj = {
					firstName : searchFirstName,
					lastName : searchLastName
			};
			 
			//var data = escape(angular.toJson({'firstName': searchFirstName , 'lastName': searchLastName}));
			
			$http.post('searchAccounts', dataObj).
		    success(function(dataBasket, status, headers, config) {
		    	$scope.myData = dataBasket;	  
		    	
		    }).
		    error(function(data, status, headers, config) {
		    	// called asynchronously if an error occurs show here
		    	alert("error " + data );
		    });
		};
		
		$scope.enrolUserInCourse = function() {			
    		var postDataObj = {
    				accountId : $scope.userId,
					courseId : $scope.selectedCourse
			};

			$http.post('enrollUserInCourse', postDataObj).
		    success(function(dataBasket, status, headers, config) {
		    	var courseTitle;
		    	 for(var i = $scope.eligibleCourses.length -1; i >= 0 ; i--){
		    		    if($scope.eligibleCourses[i].id == $scope.selectedCourse){
		    		    	courseTitle = $scope.eligibleCourses[i].title;
		    		    	$scope.eligibleCourses.splice(i, 1);
							if($scope.eligibleCourses.length > 0)
								$scope.selectedCourse = $scope.eligibleCourses[0].id;
		    		    }
		    		}
		    	//$scope.myCourses.push(dataBasket);
		    	//not working !!!
		    	// var date =  $filter('date')(new Date, 'yyyy-MM-DD')
		    	var date = new Date();
		    	var newDataObj = {
		    			course : {title:courseTitle},
		    			//startDate : new Date().toDateString()
		    			startDate : date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()  
				};

		    	 $scope.myCourses.push(newDataObj);
		    	 //$scope.items.splice(index, 1);
		    	 		    			    
		    }).
		    error(function(data, status, headers, config) {
		    	// called asynchronously if an error occurs show here
		    	alert("error " + data );
		    });
		};
		
		$scope.filterOptions = {filterText : ''	};
		
		$scope.gridOptions = {	data : 'myData',
								showGroupPanel : true,
								filterOptions : $scope.filterOptions,
								showColumnMenu : true,
								showFilter : true,
								selectedItems : $scope.mySelections,
								multiSelect : false,
								rowHeight : 22,
								columnDefs : [ {field : 'firstName', displayName : 'First Name'}, 
								               {field : 'lastName',	displayName : 'Last Name'}, 
								               {field : 'email',	displayName : 'Email'}, 
								               {field : 'status',	displayName : 'Status'} ],
							    rowTemplate: '<div ng-click="onMainGridRowSelect(row)" ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}"><div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-cell></div></div>'
				};
		$scope.coursesGridOptions = {
				data : 'myCourses',
				filterOptions : $scope.filterOptions,
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

	<div class='tab'>
	
		<div class="searchBoxStyle">
			<b>First Name</b> <input type="text" id="firstName" data-ng-model="firstName" /> 
			<b>Last Name</b> <input type="text"	 id="lastName" data-ng-model="lastName" /> 
			<input type="button"ng-click="populateGridData();" value="search" /> <br>
			<p>
				<b>Filter Columns: </b><input type="text"
					data-ng-model="filterOptions.filterText" />
			</p>
		</div>

		<div class="filler"></div>
		<div class="gridModelStyle" data-ng-grid="gridOptions"></div>	
		<div class="filler"></div>
 		<div class="gridModelStyle" id="courseDetails" ng-show="showCourseDetails">
			<table>
				<tr>
					<td width="50%">
						<table>
							<tr>
								<td> Courses Currently Entrolled into</td>
							</tr>
							<tr>
								<td>
									<div class="gridModelStyle" data-ng-grid="coursesGridOptions"></div>
								</td>
							</tr>
						</table>
					</td>
					<td> select course to enrol </td>
					<td>
						<select ng-model="selectedCourse"
							ng-options="course.id as course.title for course in eligibleCourses">
						</select>
					</td>
					<td>
						<input type="button" value="enroll" data-ng-click="enrolUserInCourse()" />
					</td>
					
			</table>
		
		</div>  
	
	</div>
	
</body>

</html>