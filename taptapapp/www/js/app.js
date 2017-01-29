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
		var email = $scope.email;
		var password = $scope.password;

		$http({
		  method: 'GET',
		  url: 'http://149.202.62.129:8080/person/search/findByEmailAndPassword?email='+email+'&password='+password
		}).then(function successCallback(response) {
			var personFound = response.data;
		    if(personFound.email == $scope.email && personFound.password == $scope.password){
		    	localStorage.setItem("firstname", personFound.firstname);
		    	localStorage.setItem("email", personFound.email);
		    	localStorage.setItem("idPerson", personFound.id);	
		    	$location.path("/bangle");
		    	console.log('connected with : ' + personFound.firstname);
		    }
		  }, function errorCallback(response) {
		    console.log(response);
		  });
	}	
})

app.controller('BangleCtrl',function($location, $scope, $http){
	if(localStorage.getItem("idPerson") == "" ||  localStorage.getItem("idPerson") == undefined){
		$location.path("/home");
	}
	var idPerson =localStorage.getItem("idPerson");
	console.log("idperson : " + idPerson);
		$http({
		  method: 'GET',
		  url: 'http://149.202.62.129:8080/api/fullinfo?idperson='+idPerson
		}).then(function successCallback(response) {
			var fullinfo = response.data;
			$scope.fullinfo = fullinfo;
			$scope.vibrations = fullinfo.vibrations;
			console.log($scope.fullinfo);
		  }, function errorCallback(response) {
		    console.log(response);
	  	});


	$scope.vibrationsSent = 0;

	$scope.sendVibration = function(){
		$http({
			method :'GET',
			url : 'http://149.202.62.129:8080/api/vibration/post?idbracelet='+$scope.fullinfo.bracelet_associated.id
		}).then(function successCallback(response){
			console.log('vibration sent to id bracelet : ' + $scope.fullinfo.bracelet_associated.id );
			$scope.vibrationsSent ++;
		}, function errorCallback(response){
			console.log(response);
		});
	}

	$scope.getVibration = function(){
		$http({
		  method: 'GET',
		  url: 'http://149.202.62.129:8080/api/fullinfo?idperson='+idPerson
		}).then(function successCallback(response) {
			var fullinfo = response.data;
			$scope.vibrations = fullinfo.vibrations;
		  }, function errorCallback(response) {
		    console.log(response);
	  	});
	}

	$scope.deconnection = function(){
		localStorage.removeItem("idPerson");
		console.log('disconnected');
		$location.path("/home");
	}

	
	
})