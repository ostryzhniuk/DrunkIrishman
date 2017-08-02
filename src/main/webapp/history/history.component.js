'use strict';

angular.module('history', ['ui.bootstrap']);

angular.
module('history').
component('history', {
    templateUrl: '/history/history.template.html',
    controller: ['$http', '$scope', '$rootScope', '$uibModal',
        function HistoryController($http, $scope, $rootScope, $uibModal) {

            if (!isAuthority('ROLE_ANONYMOUS')) {
                $http({
                    method: 'GET',
                    url: '/user/history/orders'
                }).then(function(response) {
                    $scope.orders = response.data;
                });
            } else {
                window.location.replace('#!/catalog');
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

            function isAuthority(role) {
                if ($rootScope.user == undefined) {
                    return false;
                }
                var authorities = $rootScope.user.authorities;
                for (var authority in authorities) {
                    if (authorities[authority].authority == role) {
                        return true;
                    }
                }
                return false;
            };

        }
    ]
});
