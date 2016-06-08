"use strict";
mainApp.controller("NotesController", function($scope, $rootScope, $http) {
	$scope.chosenProject = "";
	$scope.loadNoteList = false;
	$scope.loadNote = false;
	$scope.message = "";
	$scope.numNotePerPage = 10;
	$scope.defaultbarLength = 10;
	$scope.range = [];
	$scope.currentPageNotes = [];
	$scope.deleteNote = false;

	$http.get("/api/projects").success(function(data) {
		if (data.status.success) {
			$scope.projects = data.projects;
		} else {
			$scope.message = status.message;
		}
	}).error(function() {
			$scope.message = "Network Failure.";
	}).finally(function() {});

	$scope.removeNote = function(noteId) {
		$scope.deleteNote = true;
		$http.get("/api/delete/note", {
			params: {
				id: noteId
			}
		}).success(function(status) {
			if (status.success) {
				$scope.message = "Delete Note Successfully.";
				for (var i = 0; i < $scope.currentPageNotes.length; ++i)
					if ($scope.currentPageNotes[i].id == noteId)
						$scope.currentPageNotes.splice(i, 1);
			} else {
				$scope.message = "Delete Note failed.";
			}
		}).error(function() {
			$scope.message = "Network Failure.";
		}).finally(function() {});	
	};
	
	$scope.acquireNotesId = function() {
		$scope.project = $scope.chosenProject;
		$http.get("/api/notes/search", {
			params: {
				project: $scope.project
			}
		}).success(function(data) {
			$scope.loadNoteList = data.status.success;
			if (data.status.success) {
				$scope.noteId = data.noteIds;
				$scope.pageLength = Math.ceil(data.noteIds.length / $scope.numNotePerPage);
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
				$scope.searchNotes(data.noteIds.slice(($scope.currentPage - 1) * $scope.numNotePerPage, Math.min($scope.currentPage * $scope.numNotePerPage, data.noteIds.length)));
			} else {
				$scope.message = data.status.message;
			}
		}).error(function() {
			$scope.loadNoteList = false;
			$scope.message = "Network Failure.";
		}).finally(function() {
		});
	};

	$scope.searchNotes = function(noteId) {
		if (noteId.length > 0) {
			$http.get("/api/notes", {
				params: {
					note_ids : noteId
				}
			}).success(function(data) {
				$scope.loadNote = data.status.success;
				if (data.status.success) {
					$scope.currentPageNotes = new Array(data.notes.length);			
					for (var i = 0; i < data.notes.length; ++i)
						$scope.currentPageNotes[i] = data.notes[i];
				} else {
					$scope.message = data.status.message;
				}
			}).error(function() {
				$scope.loadNote = false;
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

	$scope.getCurrentPageNotes = function(page) {
		if ($scope.currentPage >= $scope.pageStart && $scope.currentPage <= $scope.pageEnd) 
			$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "basic";

		$scope.currentPage = page;
		
		$scope.searchNotes($scope.noteId.slice((page - 1) * $scope.numNotePerPage, Math.min(page * $scope.numNotePerPage, $scope.noteId.length)));

		$scope.pageButtonColor[$scope.currentPage - $scope.pageStart] = "white";
	};
});





