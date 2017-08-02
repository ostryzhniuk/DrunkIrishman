'use strict';

angular.module('orderContent', ['ui.bootstrap']);

angular.
module('orderContent')
.controller('OrderContentController', function ($http, $scope, $rootScope, order) {
    $scope.order = order;

    console.log(order);

    $scope.cancel = function () {
        $rootScope.modalInstance.close();
    };
});