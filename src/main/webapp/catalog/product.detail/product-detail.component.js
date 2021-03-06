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
                $scope.description = $scope.product.description;
                descriptionHeightSensitive();
            });

            $http.get('/product/image/' + $routeParams.productId).then(function(response) {
                $scope.photo = response.data;
            });

            $scope.addToCart = function (product){
                product.quantity = $scope.number;
                $http({
                    method: 'PUT',
                    url: '/addToCart',
                    data: product
                }).then(function(response) {
                    $rootScope.cartSize = response.data;
                });
            };

            $scope.isInStock = function (product){
                if (product != undefined && product.status == 'IN_STOCK') {
                    return true;
                } else {
                    return false;
                }
            };

            function descriptionHeightSensitive() {
                setTimeout(function(){
                    var textarea = document.querySelector('textarea');
                    var height = textarea.scrollHeight + 15;
                    textarea.style.cssText = 'height:' + height + 'px';
                },0);
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
                var request = confirm('Are you sure?\nProduct "'
                    + product.name + '" will and not be displayed.');
                if (request == true) {
                    $http({
                        method: 'PUT',
                        url: '/product/deactivate',
                        data: product
                    }).then(function(response) {
                        if (response.status == 200) {
                            var categoryName = product.category.name;
                            window.location.replace('#!/catalog/' + categoryName);
                        }
                    });
                };
            };
        }
    ]
});