"use strict";
// Define app, only one app for whole web site.
var mainApp = angular.module("mainApp", ["ngRoute"]);
// Site header.
mainApp.controller("headerController", function($scope, $rootScope, $http) {
    // Initialize user data.
    $rootScope.userData = null;
    // Fetch user info.
    $rootScope.getUserInfo = function() {
        $http.get("/api/users/info")
            .success(function(data) {
                if (data.success) {
                    $rootScope.userData = JSON.parse(data.message);
                } else {
                    $rootScope.userData = null;
                }
            }).error(function() {}).finally(function() {});
    };
    $rootScope.getUserInfo();
    // Check login expire.
    $rootScope.checkLoginExpire = function() {
        $rootScope.loginExpire = false;
        if (($scope.userData != null) && (Date.now() >= $scope.userData.loginTime + $scope.userData.expireTime)) {
            $rootScope.loginExpire = true;
        }
    };
    $rootScope.checkLoginExpire();
    // Logout.
    $rootScope.logout = function() {
        $http.get("/api/users/logout")
            .success(function(data) {
                $rootScope.userData = null;
            });
    };
});
// Some script after finishing document loading.
$(document).ready(function($) {
    $(".ui.dropdown")
        .dropdown();
});
