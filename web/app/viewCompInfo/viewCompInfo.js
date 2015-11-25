'use strict';

angular.module('myApp.viewCompInfo', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewCompInfo', {
                    templateUrl: 'app/viewCompInfo/viewCompInfo.html',
                    controller: 'viewCompInfoCtrl'
                });
            }])

        .controller('viewCompInfoCtrl', function ($http, $scope, DisableAuthInterceptor) {

            $scope.cvrapi = function () {
                DisableAuthInterceptor.enableLoader = false;
                $http.get('http://cvrapi.dk/api?search=' + $scope.searchInput + '&country=' + $scope.searchCountry)
                        .success(function (response) {
                            $scope.company = response;
                            DisableAuthInterceptor.enableLoader = true;
                        })
                        .error(function (response) {
                            console.log(response);
                            $scope.apiError = response;
                            DisableAuthInterceptor.enableLoader = true;
                        });
            };
        });