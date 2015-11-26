'use strict';

angular.module('myApp.viewHome', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewHome', {
                    templateUrl: 'app/viewHome/viewHome.html',
                    controller: 'AppLoginCtrl',
                    controllerAs: 'ctrl'
                });
            }]);

//        .controller('ViewHomeCtrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
//                this.msgFromFactory = InfoFactory.getInfo();
//                this.msgFromService = InfoService.getInfo();
//            }]);