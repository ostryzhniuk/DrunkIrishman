'use strict';

angular.module('orderView', ['ui.bootstrap']);

angular.
module('orderView').
component('orderView', {
    templateUrl: '/order/order.view/order-view.template.html',
    controller: ['$http', '$scope', '$rootScope', '$uibModal',
        function OrderViewController($http, $scope, $rootScope, $uibModal) {

            if (!isAuthority('ROLE_ANONYMOUS')) {
                $http.get('/order/status/list').then(function(response) {
                    $scope.statusList = response.data;
                    $scope.statusViewParameter = response.data[0];
                    loadOrders();
                });
            } else {
                window.location.replace('#!/catalog');
            }

            function loadOrders() {
                $http({
                    method: 'GET',
                    url: '/orders?status=' + $scope.statusViewParameter
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

            $scope.changeStatus = function(element) {
                $http({
                    method: 'PUT',
                    url: '/order/status/change',
                    data: element.order
                });
            };

            $scope.reloadOrders = function() {
                loadOrders();
            };

            function isAuthority(role) {
                if ($rootScope.user == undefined) {
                    return false;
                }
                var authorities = $rootScope.user.authorities;
                for (var authority in authorities) {
                    console.log(authorities[authority].authority)
                    if (authorities[authority].authority == role) {
                        return true;
                    }
                }
                return false;
            };

        }
    ]
});
