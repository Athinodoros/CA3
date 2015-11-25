//'use strict';

angular.module('myApp.viewCreateUser', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewCreateUser', {
                    templateUrl: 'app/viewCreateUser/viewCreateUser.html',
                    controller: 'viewCreateUserCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('viewCreateUserCtrl', ["InfoFactory", "InfoService", "$scope", "$http", function (InfoFactory, InfoService, $scope, $http) {
                $http({
                    method: 'GET',
                    url: 'api/demouser'
                }).then(function successCallback(response) {
                    $scope.data = response.data.message;
                }), function errorCallback(response) {
                    $scope.error = response.status + ": " + response.data.statusText;
                };
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);