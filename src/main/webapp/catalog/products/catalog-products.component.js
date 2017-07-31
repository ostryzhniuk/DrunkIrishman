'use strict';

angular.module('catalogProducts', [
    'ngRoute'
]);

angular.
module('catalogProducts').
component('catalogProducts', {
    templateUrl: '/catalog/products/catalog-products.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function CatalogProductsController($http, $scope, $routeParams, $rootScope) {

            $scope.categoryName = $routeParams.categoryName;
            $scope.orderProp = 'name';

            $http.get('/products/' + $routeParams.categoryName + '?loadImage=true').then(function(response) {
                $scope.products = response.data;
            });

            $scope.isInStock = function (product){
                if (product.status == 'IN_STOCK') {
                    return true;
                } else {
                    return false;
                }
            };

            $scope.addToCart = function (product){
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
                    $rootScope.cartSize = response.data;
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
                var request = confirm('Are you sure?\nProduct '
                    + product.name + ' will and not be displayed.');
                if (request == true) {
                    $http({
                        method: 'PUT',
                        url: '/product/deactivate',
                        data: {
                            id: product.id,
                            name: product.name,
                            category: product.category,
                            price: product.price,
                            capacity: product.capacity
                        }
                    }).then(function(response) {
                        if (response.status == 200) {
                            window.location.reload();
                        }
                    });
                };
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