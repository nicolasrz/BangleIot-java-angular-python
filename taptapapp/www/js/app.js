app = angular.module('app',['ngRoute']);



app.config(function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl : './templates/home.html',
		controller : 'MainCtrl'
	})

	.otherwise({redirectTo:'/'});
})	

app.controller('MainCtrl', function($scope){

	$scope.compteur = 10;

	$scope.incrementCompteur = function(){
		$scope.compteur ++;
	}
})