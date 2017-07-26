'use strict';

angular.module('catalogProducts', [
    'ngRoute'
]);

angular.
module('catalogProducts').
component('catalogProducts', {
    templateUrl: '/catalog/products/catalog-products.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope', 'basketService',
        function CatalogProductsController($http, $scope, $routeParams, $rootScope, basketService) {

            $scope.categoryName = $routeParams.categoryName;
            $scope.orderProp = 'name';

            $http.get('/products/' + $routeParams.categoryName).then(function(response) {
                $scope.products = response.data;
            });

            $scope.addToBasket = function addToBasket(product){
                // $rootScope.basketSize = basketService.addProduct(product);
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