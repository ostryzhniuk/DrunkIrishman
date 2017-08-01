'use strict';

angular.module('orderCreation', [
    'orderCreation'
]);

angular.
module('orderCreation').
component('orderCreation', {
    templateUrl: '/order/creation/order-creation.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function OrderCreationController($http, $scope, $rootScope) {

            $http.get('/userInformation').then(function(response) {
                $scope.userInformation = response.data;
                $scope.phone = $scope.userInformation.phone;
                $scope.address = $scope.userInformation.address;
            });

        }
    ]
});
