'use strict';

angular.module('myApp.viewDocumentation', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewDocumentation', {
                    templateUrl: 'app/viewDocumentation/viewDocumentation.html',
                    controller: 'ViewDocumentationCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('ViewDocumentationCtrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);