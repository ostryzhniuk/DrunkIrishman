'use strict';

angular.module('orderContent', ['ui.bootstrap']);

angular.
module('orderContent')
.controller('OrderContentController', function ($http, $scope, order) {
    $scope.order = order;
});