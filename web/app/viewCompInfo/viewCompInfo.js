'use strict';

angular.module('myApp.viewCompInfo', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/viewCompInfo', {
              templateUrl: 'app/viewCompInfo/viewCompInfo.html',
              controller: 'viewCompInfoCtrl'
            });
          }])

        .controller('viewCompInfoCtrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(res) {
            $scope.data = res.data.message;
          }, function errorCallback(res) {
            $scope.error = res.status + ": "+ res.data.statusText;
          });

        });