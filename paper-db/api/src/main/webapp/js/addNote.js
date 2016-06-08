"use strict";
mainApp.controller("AddNoteController", function($scope, $rootScope, $http) {
	$scope.project = "";
	$scope.content = "";
	$scope.message = "";
	$scope.disable = false;
	$scope.messageColor = {};
	$scope.addNote = function() {
		$scope.disable = true;
		$scope.message = "Adding...";
		$scope.messageColor.color = "green";
		$http.get("/api/add/note", {
			params: {
				project: $scope.project,
				content: $scope.content,
			}
		}).success(function(status) {
			$scope.disable = false;
			$scope.messageColor.color = "red";
			if (status.success) {
				$scope.message = "Add note successfully.";
			} else {
				$scope.message = "Add note failed.";
			}
		}).error(function() {
			$scope.message = "Network Failure.";
			$scope.messageColor.color = "red";
		}).finally(function() {
			$scope.disable = false;
		});
	};
});
