'use strict';

angular.module('cart', ['ui.bootstrap']);

angular.
module('cart').
component('cart', {
    templateUrl: '/cart/cart.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function CartController($http, $scope, $rootScope) {

            $http.get('/cart?loadImage=true').then(function(response) {
                $scope.cart = response.data;
            });

            $http.get('/cartSize').then(function(response) {
                $scope.cartSize = response.data;
            });

            $scope.cancel = function () {
                $rootScope.modalInstance.close();
            };

            $scope.isEmpty = function (){
                if ($rootScope.cartSize == 0) {
                    return true;
                } else {
                    return false;
                }
            };

            function sum(product) {
                return (product.quantity * product.price);
            };

            $scope.sumTotal = function (product){
                return sum(product).toFixed(2);
            };

            $scope.orderAmount = function orderAmount(){
                var orderAmount = 0;
                for (var product in $scope.cart) {
                    orderAmount += sum($scope.cart[product]);
                }
                return orderAmount.toFixed(2);
            };

            $scope.addToCart = function (product){
                product.quantity = 1;
                $http({
                    method: 'PUT',
                    url: '/addToCart',
                    data: {
                        id: product.id,
                        name: product.name,
                        category: product.category,
                        price: product.price,
                        capacity: product.capacity
                    }
                }).then(function(response) {
                    $scope.cartSize = response.data;
                    $rootScope.cartSize = response.data;
                    $http.get('/cart').then(function(response) {
                        $scope.cart = response.data;
                    });
                });

            };

            $scope.removeFromCart = function (product){
                $http({
                    method: 'PUT',
                    url: '/removeFromCart',
                    data: {
                        id: product.id,
                        name: product.name,
                        category: product.category,
                        price: product.price,
                        capacity: product.capacity
                    }
                }).then(function(response) {
                    $scope.cartSize = response.data;
                    $rootScope.cartSize = response.data;
                    $http.get('/cart').then(function(response) {
                        $scope.cart = response.data;
                    });
                });
            };

            $scope.decrease = function (product){
                $http({
                    method: 'PUT',
                    url: '/decrease',
                    data: {
                        id: product.id,
                        name: product.name,
                        category: product.category,
                        price: product.price,
                        capacity: product.capacity
                    }
                }).then(function(response) {
                    $scope.cartSize = response.data;
                    $rootScope.cartSize = response.data;
                    $http.get('/cart').then(function(response) {
                        $scope.cart = response.data;
                    });
                });
            };

            $scope.makeOrder = function (){
                alert("Sorry, but this service is temporarily unavailable.");
            };

        }
    ]
});


$(document).on('click', '.number-spinner button', function () {
    var btn = $(this),
        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
        newValue = 0;

    if (btn.attr('data-dir') == 'up') {
        if (oldValue < 1000) {
            newValue = parseInt(oldValue) + 1;
        } else {
            newValue = 1000;
        }
    } else {
        if (oldValue > 1) {
            newValue = parseInt(oldValue) - 1;
        } else {
            newValue = 1;
        }
    }
    btn.closest('.number-spinner').find('input').val(newValue).trigger("change");
});

$(document).on('blur', '.number-spinner input', function () {
    var input = $(this),
        value = input.val().trim(),
        newValue = value;

        if (value > 1000) {
            newValue = 1000;
        } else if (value < 1) {
            newValue = 1;
        }

    input.val(newValue).trigger("change");
});