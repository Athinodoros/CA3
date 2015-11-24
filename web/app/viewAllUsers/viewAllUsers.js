'use strict';

angular.module('myApp.viewAllUsers', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/viewAllUsers', {
    templateUrl: 'app/viewAllUsers/viewAllUsers.html',
    controller: 'ViewAllUsersCtrl'
  });
}])

.controller('ViewAllUsersCtrl', function($http,$scope) {
  $http.get('api/demoadmin')
            .success(function (data, status, headers, config) {
              $scope.data = data;
            })
            .error(function (data, status, headers, config) {
              
             });
 
});