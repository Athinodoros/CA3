'use strict';

angular.module('myApp.viewExchange', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewExchange', {
                    templateUrl: 'app/viewExchange/viewExchange.html',
                    controller: 'ViewExchangeCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('ViewExchangeCtrl', function (InfoFactory, InfoService, $http, $scope, DisableAuthInterceptor) {
            function exInfo() {
                DisableAuthInterceptor.enableLoader = false;
//                $http({
//                    method: 'GET',
//                    url: 'api/add',
//                    headers: {
//                        'X-API-TOKEN': undefined
//                    }
//                }
//                ).then(function successCallback(response) {
//                        $scope.data = response.data.message;
//                        DisableAuthInterceptor.enableLoader = true;
//
//                    }), function errorCallback(response) {
//                        $scope.error = response.status + ": " + response.data.statusText;
//                        DisableAuthInterceptor.enableLoader = true;
//                  };
                $http({
                    method: 'GET',
                    url: 'api/currency/dailyrates'
                }).then(function successCallback(response) {
                    $scope.data = response.data;
                    DisableAuthInterceptor.enableLoader = true;
                }), function errorCallback(response) {
                    $scope.error = response.status + ": " + response.data.statusText;
                    DisableAuthInterceptor.enableLoader = true;
                };
            }

            exInfo();

            $scope.convert = function () {
                return $scope.converter.result = ($scope.converter.from * $scope.converter.amount) / $scope.converter.to;
            };


            function getCalc() {
                $http({
                    method: 'GET',
                    url: 'api/currency/' + $scope.converter.amount + '/' + $scope.converter.from + '/' + $scope.converter.to
                }).then(function successCallback(response) {
                    $scope.converter = response.data;
                }), function errorCallback(response) {
                    $scope.converter = response;
                };
            }


        });