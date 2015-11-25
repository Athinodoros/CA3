'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'ui.bootstrap',
    'myApp.security',
    'myApp.viewHome',
    'myApp.viewAllUsers',
    'myApp.viewCompInfo',
    'myApp.viewDocumentation',
    'myApp.viewExchange',
    'myApp.viewLoginSection',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services',
    'myApp.viewCreateUser'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/viewHome'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        });


