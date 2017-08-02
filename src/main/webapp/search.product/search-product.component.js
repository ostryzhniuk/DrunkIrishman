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

            $scope.addToCart = function (product){
                $http({
                    method: 'PUT',
                    url: '/addToCart',
                    data: product
                }).then(function(response) {
                    $rootScope.cartSize = response.data;
                });
            };

            $scope.isInStock = function (product){
                if (product.status == 'IN_STOCK') {
                    return true;
                } else {
                    return false;
                }
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

            document.getElementById('open-csv-button').addEventListener("click", function() {
                document.getElementById('open-csv-input').click();
            });

            csvProductReader.onload = function () {
                var csvBase64 = csvProductReader.result;
                createProducts(csvBase64);
            };

            function createProducts(csvBase64) {
                $http({
                    method: 'POST',
                    url: '/product/create/byCsv',
                    data: {
                        base64SourceData: csvBase64
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        alert('Data has been successfully saved!');
                        window.location.reload();
                    }
                },function errorCallback(response) {
                    alert('Sorry, but an error occurred.\n' +
                        'Maybe You data is wrong. Try again, please.');
                });
            };
        }
    ]
});