'use strict';

angular.module('orderView', []);

angular.
module('orderView').
component('orderView', {
    templateUrl: '/order/order.view/order-view.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function OrderViewController($http, $scope, $rootScope) {

            $http({
                method: 'GET',
                url: '/orders?status=' + 'IN_PROCESS',
            }).then(function(response) {
                $scope.orders = response.data;
            });

            $scope.showOrderContent = function (order) {
                $rootScope.modalInstance = $uibModal.open({
                    component: 'cart',
                    windowClass: 'app-modal-window',
                    size: 'lg',
                    backdrop: true,
                    resolve: {
                        order: function () {
                            return order;
                        }
                    }
                });
            };

        }
    ]
});
