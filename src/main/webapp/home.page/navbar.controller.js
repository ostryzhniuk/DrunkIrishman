'use strict';

angular.
module('shopApp').
controller('navbarCtrl', function ($http, $scope, $sessionStorage) {

    $scope.orderProp = 'name';
    $scope.goodsQuantity = 0;

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });

    $scope.addToBasket = function addToBasket(product){
        $http({
            method: 'PUT',
            url: '/addToBasket',
            data: product
        }).then(function(response) {
            $sessionStorage.goodsQuantity = response.data;
            $scope.goodsQuantity = response.data;
        });
    };

});