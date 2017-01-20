app = angular.module('app',['ngRoute']);



app.config(function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl : './templates/home.html',
		controller : 'MainCtrl'
	})

	.when('/bangle',{
		templateUrl : './templates/bangle.html',
		controller: 'BangleCtrl'
	})

	.otherwise({redirectTo:'/'});
})	

app.controller('MainCtrl', function($scope, $http, $location){

	$scope.connection = function(){
		var firstname = $scope.firstname;
		var lastname = $scope.lastname;

		$http({
		  method: 'GET',
		  url: 'http://localhost9:8080/person/search/findByFirstnameAndLastname?firstname='+firstname+'&lastname='+lastname
		}).then(function successCallback(response) {
			var personFound = response.data;
		    if(personFound.firstname == $scope.firstname && personFound.lastname == $scope.lastname){
		    	localStorage.setItem("lastname", personFound.lastname);
		    	localStorage.setItem("firstname", personFound.firstname);
		    	localStorage.setItem("idPerson", personFound.id);	
		    	$location.path("/bangle");
		    }
		  }, function errorCallback(response) {
		    alert(response);
		  });
	}	
})

app.controller('BangleCtrl',function($location, $scope, $http){
	if(localStorage.getItem("lastname") == "" ||  localStorage.getItem("lastname") == undefined){
		$location.path("/home");
	}

	$scope.sendVibration = function(){

	}

	$scope.getVibration = function(){
		var idPerson =localStorage.getItem("idPerson");

		$http({
		  method: 'GET',
		  url: 'http://localhost9:8080/person/'+idPerson+'/bracelet'
		}).then(function successCallback(response) {
			var bracelet = response.data;
			var idBracelet = bracelet.id;
			
			$http({
			    method: 'GET',
		  		url: 'http://localhost9:8080/bracelet/'+idBracelet+'/bracelets'
			}).then(function successCallback(response){
				var associatedBraceletId = response.data._embedded.bracelet[0].id;
				console.log(associatedBraceletId);
			}, function errorCallback(response){
				alert(reponse);
			})



		  }, function errorCallback(response) {
		    alert(response);
	  	});
	
	}


	
})