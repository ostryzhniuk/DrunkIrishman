'use strict';

angular.module('productDetail', [
    'ngRoute'
]);

angular.
module('productDetail').
component('productDetail', {
    templateUrl: '/catalog/product.detail/product-detail.template.html',
    controller: ['$http', '$scope', '$routeParams',
        function ProductDetailController($http, $scope, $routeParams) {

            $scope.categoryName = $routeParams.categoryName;

            $http.get('/product/' + $routeParams.productId).then(function(response) {
                $scope.product = response.data;
            });

        }
    ]
});