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

            $scope.isInStock = function (product){
                if (product.status == 'IN_STOCK') {
                    return true;
                } else {
                    return false;
                }
            };

            $scope.addToBasket = function (product){
                // $rootScope.basketSize = basketService.addProduct(product);
                $http({
                    method: 'PUT',
                    url: '/addToBasket',
                    data: product
                }).then(function(response) {
                    $rootScope.basketSize = response.data;
                });
            };

            $http.get('/currentUser').then(function(response) {
                $rootScope.user = response.data;
            });

            $scope.isAuthority = function (role) {
                if ($rootScope.user == undefined) {
                    return false;
                }
                var authorities = $rootScope.user.authorities;
                for (var authority in authorities) {
                    if (authorities[authority].authority == role) {
                        return true;
                    }
                }
                return false;
            };

        }
    ]
});