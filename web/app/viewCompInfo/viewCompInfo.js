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
                $scope.error = res.status + ": " + res.data.statusText;
            });

            $scope.cvrapi = function () {
                $http.get('http://cvrapi.dk/api?search=' + $scope.searchInput + '&country=' + $scope.searchCountry)
                        .success(function (response) {
                            $scope.company = response;
                        })
                        .error(function (response) {
                            console.log(response);
                            $scope.apiError = response;
                        });
            };
        });