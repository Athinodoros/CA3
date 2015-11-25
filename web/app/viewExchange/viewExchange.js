'use strict';

angular.module('myApp.viewExchange', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewExchange', {
                    templateUrl: 'app/viewExchange/viewExchange.html',
                    controller: 'ViewExchangeCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('ViewExchangeCtrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);