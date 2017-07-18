'use strict';

angular.
module('shopApp').
controller('navbarCtrl', function ($http, $scope, $rootScope) {

    $scope.orderProp = 'name';

    $rootScope.goodsQuantity = $http.get('/goodsQuantity').then(function(response) {
        $rootScope.goodsQuantity = response.data;
    });

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });

    $scope.addToBasket = function addToBasket(product){
        $http({
            method: 'PUT',
            url: '/addToBasket',
            data: product
        }).then(function(response) {
            $rootScope.goodsQuantity = response.data;
        });
    };

});