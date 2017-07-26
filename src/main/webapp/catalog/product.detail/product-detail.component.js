'use strict';

angular.module('productDetail', [
    'ngRoute'
]);

angular.
module('productDetail').
component('productDetail', {
    templateUrl: '/catalog/product.detail/product-detail.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function ProductDetailController($http, $scope, $routeParams, $rootScope) {

            $scope.number = 1;

            $scope.categoryName = $routeParams.categoryName;

            $http.get('/product/' + $routeParams.productId).then(function(response) {
                $scope.product = response.data;
            });

            $scope.addToBasket = function (product){
                product.counter = $scope.number;
                console.log($scope.number);
                $http({
                    method: 'PUT',
                    url: '/addToBasket',
                    data: product
                }).then(function(response) {
                    $rootScope.basketSize = response.data;
                });
            };

        }
    ]
});