angular.module('myApp.viewCreateUser', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/viewCreateUser', {
                    templateUrl: 'app/viewCreateUser/viewCreateUser.html',
                    controller: 'viewCreateUserCtrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('viewCreateUserCtrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
            }]);