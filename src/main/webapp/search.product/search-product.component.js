'use strict';

angular.module('searchProduct', [
    'ngRoute'
]);

angular.
module('searchProduct').
component('searchProduct', {
    templateUrl: '/catalog/products/catalog-products.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function SearchProductController($http, $scope, $routeParams, $rootScope) {

            $scope.categoryName = 'Search';
            $scope.orderProp = 'name';

            $http.get('/search/' + $routeParams.searchParameter).then(function(response) {
                $scope.products = response.data;
                if ($scope.products.length == 0) {
                    $scope.categoryName = 'Sorry, but nothing matched your search criteria. ' +
                        'Please try again with some different keywords.';
                } else {
                    $scope.categoryName = $scope.products.length + ' results for: ' + $routeParams.searchParameter;
                }
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