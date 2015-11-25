'use strict';

angular.module('myApp.viewExchange', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewExchange', {
                    templateUrl: 'app/viewExchange/viewExchange.html',
                    controller: 'ViewExchangeCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('ViewExchangeCtrl', ["InfoFactory", "InfoService", "$http", "$scope", function (InfoFactory, InfoService, $http, $scope) {
                $http({
                    method: 'GET',
                    url: 'api/demouser'
                }).then(function successCallback(response) {
                    $scope.data = response.data.message;
                }), function errorCallback(response) {
                    $scope.error = response.status + ": " + response.data.statusText;
                };

                $scope.msgFromFactory = InfoFactory.getInfo();
                $scope.msgFromService = InfoService.getInfo();
            }]);