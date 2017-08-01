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

            $scope.errorMessage = '';

            $http.get('/userInformation').then(function(response) {
                $scope.userInformation = response.data;
                $scope.phone = $scope.userInformation.phone;
                $scope.address = $scope.userInformation.address;
            });
            
            function isDelivery() {
                return document.getElementById("delivery").checked;
            }

            function getCurrentDateTime() {
                return new Date().toLocaleString();
            }

            $scope.processOrder = function() {
                console.log(getCurrentDateTime());
                $scope.errorMessage = '';

                $http({
                    method: 'POST',
                    url: '/order/create',
                    data: {
                        date: getCurrentDateTime(),
                        address: $scope.address,
                        phone: $scope.phone,
                        delivery: isDelivery(),
                        user: $scope.userInformation
                    }
                }).then(function(response) {
                    window.location.replace('#!/catalog');
                    window.location.reload();
                },function errorCallback(response) {
                    if (response.status == 403) {
                        $scope.errorMessage = 'Access forbidden!';
                    } else {
                        $scope.errorMessage = 'Sorry, but system error occurred. Try again later, please.';
                    }
                });
            }

        }
    ]
});
