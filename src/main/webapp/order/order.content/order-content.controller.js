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
        $scope.orderContentList = response.data;
        console.log($scope.orderContentList);

    });

    $scope.cancel = function () {
        $rootScope.modalInstance.close();
    };

    $scope.sumPrice = function (product, quantity){
        return (quantity * product.price).toFixed(2);
    };
});