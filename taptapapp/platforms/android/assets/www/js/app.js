app = angular.module('app',['ngRoute']);



app.config(function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl : './templates/home.html',
		controller : 'MainCtrl'
	})

	.otherwise({redirectTo:'/'});
})	

app.controller('MainCtrl', function($scope, $http){

	$scope.connection = function(){
		var firstname = $scope.firstname;
		var lastname = $scope.lastname;
		
		$http({
		  method: 'GET',
		  url: 'http://149.202.62.129:8080/person?firstname='+firstname+'&lastname='+lastname
		}).then(function successCallback(response) {
			    console.log(response);
		  }, function errorCallback(response) {
		    alert('error maggle');
		  });
	}
	
	
})