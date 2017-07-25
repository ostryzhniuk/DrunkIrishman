'use strict';

angular.module('basket', ['ui.bootstrap']);

angular.
module('basket').
component('basket', {
    templateUrl: '/basket/basket.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function BasketController($http, $scope, $rootScope) {

            $http.get('/basket').then(function(response) {
                $scope.basket = response.data;
            });

            $http.get('/basketSize').then(function(response) {
                $scope.basketSize = response.data;
            });

            $scope.cancel = function () {
                $rootScope.modalInstance.close();
            };

            $scope.isEmpty = function isEmpty(){
                if ($scope.basketSize == 0) {
                    return true;
                } else {
                    return false;
                }
            };

        }
    ]
});