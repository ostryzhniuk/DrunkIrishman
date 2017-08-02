'use strict';

angular.module('history', ['ui.bootstrap']);

angular.
module('history').
component('history', {
    templateUrl: '/history/history.template.html',
    controller: ['$http', '$scope', '$rootScope', '$uibModal',
        function HistoryController($http, $scope, $rootScope, $uibModal) {

            $http.get('/order/status/list').then(function(response) {
                $scope.statusList = response.data;
                $scope.statusViewParameter = response.data[0];
                loadOrders();
            });

            function loadOrders() {
                $http({
                    method: 'GET',
                    url: '/user/history/orders'
                }).then(function(response) {
                    $scope.orders = response.data;
                });
            }

            $scope.showOrderContent = function(order) {
                $rootScope.modalInstance = $uibModal.open({
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
