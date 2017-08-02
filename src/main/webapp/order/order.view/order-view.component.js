'use strict';

angular.module('orderView', ['ui.bootstrap']);

angular.
module('orderView').
component('orderView', {
    templateUrl: '/order/order.view/order-view.template.html',
    controller: ['$http', '$scope', '$rootScope', '$uibModal',
        function OrderViewController($http, $scope, $rootScope, $uibModal) {

            $http({
                method: 'GET',
                url: '/orders?status=' + 'IN_PROCESS',
            }).then(function(response) {
                $scope.orders = response.data;
            });

            $scope.showOrderContent = function (order) {
                $rootScope.modalInstance = $uibModal.open({
                    // component: 'orderContent',
                    templateUrl: '/order/order.content/order-content.template.html',
                    controller: 'OrderContentController',
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
