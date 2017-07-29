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

            $scope.delete = function (product) {
                /*var request = confirm('Are you sure?\nThis action CANNOT be undone! This will ' +
                    'permanently delete the ' + product.id + ' category and all products of this category.');
                if (request == true) {
                    $http.delete('/product/delete/' + product.id).then(function(response) {
                        window.location.reload();
                    });
                };*/
            };

        }
    ]
});


function mouseOver2(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOut2(element){
    element.childNodes[1].style.visibility='hidden';
};