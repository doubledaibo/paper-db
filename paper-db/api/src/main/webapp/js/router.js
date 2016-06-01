"use strict";
mainApp.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when("/web/index", {
            templateUrl: "templates/index.html",
            controller: "IndexController"
        })
        .when("/web/users/register", {
            templateUrl: "templates/register.html",
            controller: "RegisterController"
        })
        .when("/web/users/login", {
            templateUrl: "templates/login.html",
            controller: "LoginController"
        })
        .when("/web/users", {
            templateUrl: "templates/users.html",
            controller: "UsersController"
        })
        .when("/web/users/:userId", {
            templateUrl: "template/users_id.html",
            controller: "UsersIdController"
        })
        .when("/web/restaurants", {
            templateUrl: "templates/restaurants.html",
            controller: "RestaurantsController"
        })
        .when("/web/restaurants/:restaurantId", {
            templateUrl: "templates/restaurant.html",
            controller: "RestaurantsController"
        })
        .when("/", {
            redirectTo: "/web/index"
        })
        .otherwise({
            templateUrl: "templates/404.html"
        });
    $locationProvider.html5Mode(true);
});
$(document).ready(function($) {});
