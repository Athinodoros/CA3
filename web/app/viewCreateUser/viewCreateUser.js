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
                $scope.answer = '';
                $scope.insertNewUser = function () {
                    $http({
                        method: 'GET',
                        url: 'api/add/'+$scope.username.trim()+'/'+ $scope.password.trim() + '/'+$scope.role.trim() 
                    }).then(function successCallback(response) {
                        $scope.answer = response;
                    }), function errorCallback(response) {
                        $scope.answer = response ;
                    };
                };
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);