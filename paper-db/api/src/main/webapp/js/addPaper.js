"use strict";
mainApp.controller("AddPaperController", function($scope, $rootScope, $http) {
	$scope.name = "";
	$scope.project = "";
	$scope.content = "";
	$scope.highlight = "";
	$scope.limitation = "";
	$scope.bib = "";
	$scope.year = "";
	$scope.message = "";
	$scope.disable = false;
	$scope.messageColor = {};
	$scope.addPaper = function() {
		$scope.disable = true;
		$scope.message = "Adding...";
		$scope.messageColor.color = "green";
		$http.get("/api/add/paper", {
			params: {
				name: $scope.name,
				project: $scope.project,
				content: $scope.content,
				highlight: $scope.highlight,
				limit: $scope.limitation,
				bib: $scope.bib,
				year: $scope.year
			}
		}).success(function(status) {
			$scope.disable = false;
			$scope.messageColor.color = "red";
			if (status.success) {
				$scope.message = "Add paper successfully.";
			} else {
				$scope.message = "Add paper failed.";
			}
		}).error(function() {
			$scope.message = "Network Failure.";
			$scope.messageColor.color = "red";
		}).finally(function() {
			$scope.disable = false;
		});
	};
});
