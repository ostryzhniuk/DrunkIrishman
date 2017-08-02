'use strict';

angular.module('orderContent', ['ui.bootstrap']);

angular.
module('orderContent')
.controller('OrderContentController', function ($http, $scope, $rootScope, order) {
    $scope.order = order;

    $http({
        method: 'GET',
        url: '/order/content?orderId=' + order.id
    }).then(function(response) {
        $scope.orderContent = response.data;
        console.log($scope.orderContent);
    });

    $scope.cancel = function () {
        $rootScope.modalInstance.close();
    };
});