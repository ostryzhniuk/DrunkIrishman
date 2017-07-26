'use strict';

angular.module('basket', ['ui.bootstrap']);

angular.
module('basket').
component('basket', {
    templateUrl: '/basket/basket.template.html',
    controller: ['$http', '$scope', '$rootScope', 'basketService',
        function BasketController($http, $scope, $rootScope, basketService) {

            $http.get('/basket').then(function(response) {
                $scope.basket = response.data;
            });

            $http.get('/basketSize').then(function(response) {
                $scope.basketSize = response.data;
            });

            $scope.cancel = function () {
                $rootScope.modalInstance.close();
            };

            $scope.isEmpty = function isEmpty(){
                if ($rootScope.basketSize == 0) {
                    return true;
                } else {
                    return false;
                }
            };

            function sum(product) {
                return (product.counter * product.price);
            };

            $scope.sumTotal = function sumTotal(product){
                return sum(product).toFixed(2);
            };

            $scope.orderAmount = function orderAmount(){
                var orderAmount = 0;
                for (var product in $scope.basket) {
                    orderAmount += sum($scope.basket[product]);
                }
                return orderAmount.toFixed(2);
            };

            $scope.addToBasket = function addToBasket(product){
                product.counter = 1;
                $http({
                    method: 'PUT',
                    url: '/addToBasket',
                    data: product
                }).then(function(response) {
                    $scope.basketSize = response.data;
                    $rootScope.basketSize = response.data;
                    $http.get('/basket').then(function(response) {
                        $scope.basket = response.data;
                    });
                });

                /*$scope.basketSize = basketService.addProduct(product);
                $rootScope.basketSize = $scope.basketSize;
                $http.get('/basket').then(function(response) {
                    $scope.basket = response.data;
                });*/

            };

            $scope.removeFromBasket = function removeFromBasket(product){
                $http({
                    method: 'PUT',
                    url: '/removeFromBasket',
                    data: product
                }).then(function(response) {
                    $scope.basketSize = response.data;
                    $rootScope.basketSize = response.data;
                    $http.get('/basket').then(function(response) {
                        $scope.basket = response.data;
                    });
                });
            };

            $scope.decrease = function decrease(product){
                $http({
                    method: 'PUT',
                    url: '/decrease',
                    data: product
                }).then(function(response) {
                    $scope.basketSize = response.data;
                    $rootScope.basketSize = response.data;
                    $http.get('/basket').then(function(response) {
                        $scope.basket = response.data;
                    });
                });
            };

            $scope.makeOrder = function makeOrder(){
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