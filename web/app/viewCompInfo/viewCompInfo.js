'use strict';

angular.module('myApp.viewCompInfo', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewCompInfo', {
                    templateUrl: 'app/viewCompInfo/viewCompInfo.html',
                    controller: 'viewCompInfoCtrl'
                });
            }])

        .controller('viewCompInfoCtrl', function ($http, $scope, DisableAuthInterceptor) {
            $scope.er = false;
            $scope.apiError = "Such company does not exist ";
            $scope.cvrapi = function () {
                DisableAuthInterceptor.enableLoader = false;
                $http.get('http://cvrapi.dk/api?search=' + $scope.searchInput + '&country=' + $scope.searchCountry)
                        .success(function (response) {
                            $scope.er = false;
                            $scope.company = response;
                            if ($scope.company.error == "NOT_FOUND") {
                                $scope.er = true;
                            }
                            $scope.error = response.error;
                            DisableAuthInterceptor.enableLoader = true;
                        })
                        .error(function (response) {
                            console.log(response);
                            $scope.er = true;
                            DisableAuthInterceptor.enableLoader = true;
                        });
            };
        });