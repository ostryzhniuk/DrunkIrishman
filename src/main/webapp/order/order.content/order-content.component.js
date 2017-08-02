'use strict';

angular.module('orderContent', ['ui.bootstrap']);

angular.
module('orderContent').
component('orderContent', {
    templateUrl: '/cart/cart.template.html',
    controller: ['$http', '$scope', 'order',
        function OrderContentController($http, $scope, order) {

            $scope.order = order;

        }
    ]
});