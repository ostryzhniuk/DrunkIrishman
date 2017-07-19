'use strict';

angular.module('basket', []);

angular.
module('basket').
component('basket', {
    templateUrl: '/basket/basket.template.html',
    controller: ['$http', '$scope',
        function BasketController($http, $scope) {

            $http.get('/basket').then(function(response) {
                $scope.basket = response.data;
            });

        }
    ]
});