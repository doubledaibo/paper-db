"use strict";
mainApp.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when("/web/index", {
            templateUrl: "templates/index.html",
            controller: "IndexController"
        })
	.when("/web/home", {
	    templateUrl: "templates/home.html",
	    controller: "HomeController"
	})
        .when("/web/users/register", {
            templateUrl: "templates/register.html",
            controller: "RegisterController"
        })
        .when("/web/users/login", {
            templateUrl: "templates/login.html",
            controller: "LoginController"
        })
        .when("/web/papers", {
            templateUrl: "templates/papers.html",
            controller: "PapersController"
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
