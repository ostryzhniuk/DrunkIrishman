'use strict';

angular.module('productCreator', [
    'ngRoute'
]);

angular.
module('productCreator').
component('productCreator', {
    templateUrl: '/catalog/product.detail/product.editor/product-editor.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function ProductDetailController($http, $scope, $routeParams, $rootScope) {

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

            $scope.save = function () {
                $http({
                    method: 'POST',
                    url: '/product/create',
                    data: {
                        name: $scope.name,
                        price: $scope.price,
                        capacity: $scope.capacity,
                        category: $scope.category,
                        description: $scope.description
                    }
                }).then(function(response) {

                });
            };

        }
    ]
});