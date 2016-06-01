"use strict";
mainApp.controller("LoginController", function($scope, $rootScope, $http) {
    // Initialize.
    $scope.account = "";
    $scope.password = "";
    $scope.message = "";
    $scope.messageColor = {};
    $scope.editBlock = false;
    // Check login expire again.
    $rootScope.checkLoginExpire();
    // Show login expire message.
    if ($rootScope.loginExpire) {
        $scope.account = $scope.userData.name;
        $scope.message = "登陆已超时，请重新登陆。";
        $scope.messageColor.color = "red";
    }
    // Login.
    $scope.login = function() {
        $scope.editBlock = true;
        $scope.message = "登陆中....";
        $scope.messageColor.color = "green";
        $http.get("/api/users/login", {
                params: {
                    account: $scope.account,
                    password: $scope.password
                }
            })
            .success(function(data) {
                $scope.message = data.message;
                if (data.success) {
                    $rootScope.getUserInfo();
                    window.location.href = "/web/index";
                } else {
                    $scope.messageColor.color = "red";
                }
            }).error(function() {
                $scope.message = "网络出错了。"
                $scope.messageColor.color = "red";
            }).finally(function() {
                $scope.editBlock = false;
            });
    };
});
