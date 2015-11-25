'use strict';

angular.module('myApp.viewExchange', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewExchange', {
                    templateUrl: 'app/viewExchange/viewExchange.html',
                    controller: 'ViewExchangeCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('ViewExchangeCtrl', ["InfoFactory", "InfoService", "$http", function (InfoFactory, InfoService, $http) {
                this.data = "";
                        $http({
                    method: 'GET',
                    url: 'api/demouser'
                }).then(function successCallback(response) {
                    this.data = response.data.message;
                }), function errorCallback(response) {
                    this.error = response.status + ": " + response.data.statusText;
                };
                
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);