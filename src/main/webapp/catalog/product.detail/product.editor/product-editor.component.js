'use strict';

angular.module('productEditor', [
    'ngRoute'
]);

angular.
module('productEditor').
component('productEditor', {
    templateUrl: '/catalog/product.detail/product.editor/product-editor.template.html',
    controller: ['$http', '$scope', '$routeParams',
        function ProductEditorController($http, $scope, $routeParams) {

            $scope.editor = true;
            $scope.action = 'Edit';

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

            $http.get('/product/' + $routeParams.productId).then(function(response) {
                $scope.product = response.data;
                $scope.name = $scope.product.name;
                $scope.category = $scope.product.category;
                $scope.price = $scope.product.price;
                $scope.capacity = $scope.product.capacity;
                $scope.description = $scope.product.description;
            });

            $scope.save = function () {
                $http({
                    method: 'PUT',
                    url: '/product/update',
                    data: {
                        id: $scope.product.id,
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