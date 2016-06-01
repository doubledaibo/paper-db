"use strict";
mainApp.controller("RegisterController", function($scope, $http) {
    // Initialize.
    $scope.name = "";
    $scope.email = "";
    $scope.password = "";
    $scope.passwordv = "";
    $scope.message = "";
    $scope.messageColor = {};
    $scope.editBlock = false;
    // Check whether input is valid.
    $scope.check = function() {
        $scope.messageColor.color = "red";
        if ($scope.name == "") {
            $scope.message = "用户名不能为空。";
            return false;
        }
        if ($scope.email == "") {
            $scope.message = "邮箱不能为空。";
            return false;
        }
        if ($scope.password == "") {
            $scope.message = "密码不能为空。";
            return false;
        }
        if ($scope.password != $scope.passwordv) {
            $scope.message = "两次输入的密码不一致。";
            return false;
        }
        return true;
    };
    // Register.
    $scope.register = function() {
        // Check input validation first.
        if (!$scope.check()) {
            return;
        }
        $scope.editBlock = true;
        $scope.message = "注册中.....";
        $scope.messageColor.color = "green";
        $http.get("/api/users/register", {
                params: {
                    name: $scope.name,
                    email: $scope.email,
                    password: $scope.password
                }
            })
            .success(function(data) {
                $scope.editBlock = false;
                $scope.message = data.message;
                if (data.success) {
                    window.location.href = "/web/users/login";
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
