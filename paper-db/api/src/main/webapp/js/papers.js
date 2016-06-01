"use strict";
mainApp.controller("PapersController", function($scope, $rootScope, $http) {
	$scope.chosenProject = "";
	$scope.loadPaperList = false;
	$scope.loadPaper = false;
	$scope.message = "";
	$scope.numPaperPerPage = 10;
	$scope.defaultbarLength = 10;
	$scope.range = [];
	$scope.currentPagePapers = [];
	$scope.deletePaper = false;

	$http.get("/api/projects").success(function(data) {
		if (data.status.success) {
			$scope.projects = data.projects;
		} else {
			$scope.message = status.message;
		}
	}).error(function() {
			$scope.message = "Network Failure.";
	}).finally(function() {});

	$scope.removePaper = function(paperId) {
		$scope.deletePaper = true;
		$http.get("/api/delete/paper", {
			params: {
				id: paperId
			}
		}).success(function(status) {
			if (status.success) {
				$scope.message = "Delete Paper Successfully.";
				for (var i = 0; i < $scope.currentPagePapers.length; ++i)
					if ($scope.currentPagePapers[i].id == paperId)
						$scope.currentPagePapers.splice(i, 1);
			} else {
				$scope.message = "Delete Paper failed.";
			}
		}).error(function() {
			$scope.message = "Network Failure.";
		}).finally(function() {});	
	};
	
	$scope.acquirePapersId = function() {
		$scope.project = $scope.chosenProject;
		$http.get("/api/papers/search", {
			params: {
				project: $scope.project
			}
		}).success(function(data) {
			$scope.loadPaperList = data.status.success;
			if (data.status.success) {
				$scope.paperId = data.paperIds;
				$scope.pageLength = Math.ceil(data.paperIds.length / $scope.numPaperPerPage);
				$scope.barLength = Math.min($scope.defaultbarLength, $scope.pageLength);
				$scope.pageStart = 1;
				$scope.pageEnd = $scope.barLength;
				$scope.range = new Array($scope.barLength);		
				$scope.pageButtonColor = new Array($scope.barLength);
				for (var i = 0; i < $scope.barLength; ++i) {
					$scope.pageButtonColor[i] = "basic";
					$scope.range[i] = i + 1;
				}
				$scope.currentPage = 1;
				$scope.pageButtonColor[$scope.currentPage - 1] = "white";
				$scope.searchPapers(data.paperIds.slice(($scope.currentPage - 1) * $scope.numPaperPerPage, Math.min($scope.currentPage * $scope.numPaperPerPage, data.paperIds.length)));
			} else {
				$scope.message = data.status.message;
			}
		}).error(function() {
			$scope.loadPaperList = false;
			$scope.message = "Network Failure.";
		}).finally(function() {
		});
	};

	$scope.searchPapers = function(paperId) {
		if (paperId.length > 0) {
			$http.get("/api/papers", {
				params: {
					paper_ids : paperId
				}
			}).success(function(data) {
				$scope.loadPaper = data.status.success;
				if (data.status.success) {
					$scope.currentPagePapers = new Array(data.papers.length);			
					for (var i = 0; i < data.papers.length; ++i)
						$scope.currentPagePapers[i] = data.papers[i];
				} else {
					$scope.message = data.status.message;
				}
			}).error(function() {
				$scope.loadPaper = false;
				$scope.message = "Network Failure.";
			}).finally(function() {
			});
		}
	};

	$scope.decreasePageList = function() {
		if ($scope.pageStart > 1) {			
			if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd)
				$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "basic";

			--$scope.pageStart;
			--$scope.pageEnd;
			for (var i = $scope.pageStart; i <= $scope.pageEnd; ++i) 
				$scope.range[i - $pageStart] = i;

			if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd)
				$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "white";
		}
	};

	$scope.increasePageList = function() {
		if ($scope.pageEnd < $scope.pageLength) {	
			if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd) 
				$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "basic";

			++$scope.pageStart;
			++$scope.pageEnd;
			for (var i = $scope.pageStart; i <= $scope.pageEnd; ++i) 
				$scope.range[i - $pageStart] = i;

			if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd) 
				$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "white";
		}
	};

	$scope.getCurrentPagePapers = function(page) {
		if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd) 
			$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "basic";

		$scope.currentPage = page;
		
		$scope.searchPapers($scope.paperId.slice((page - 1) * $scope.numPaperPerPage, Math.min(page * $scope.numPaperPerPage, $scope.paperId.length)));

		$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "white";
	};
});





