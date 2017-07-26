'use strict';

angular.module('searchProduct', [
    'ngRoute'
]);

angular.
module('searchProduct').
component('searchProduct', {
    // templateUrl: '/search.product/search-product.template.html',
    templateUrl: '/catalog/products/catalog-products.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function SearchProductController($http, $scope, $routeParams, $rootScope) {

            $scope.categoryName = 'Search';
            $scope.orderProp = 'name';

            $http.get('/search/' + $routeParams.searchParameter).then(function(response) {
                $scope.products = response.data;
            });

            $scope.addToBasket = function (product){
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