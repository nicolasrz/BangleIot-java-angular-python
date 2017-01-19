app = angular.module('app',[]);

app.controller('MainCtrl', function($scope){

	$scope.compteur = 10;

	$scope.incrementCompteur = function(){
		$scope.compteur ++;
	}
})