app = angular.module('app',[]);

app.controller('MainCtrl', function($scope){

	$scope.compteur = 10;



	$interval(function(){
		$scope.compteur ++;
	}, 1000)

	$scope.incrementCompteur = function(){
		$scope.compteur ++;
	}
})