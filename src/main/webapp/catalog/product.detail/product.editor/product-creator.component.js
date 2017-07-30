'use strict';

angular.module('productCreator', []);

angular.
module('productCreator').
component('productCreator', {
    templateUrl: '/catalog/product.detail/product.editor/product-editor.template.html',
    controller: ['$http', '$scope',
        function ProductCreatorController($http, $scope) {

            $scope.editor = false;
            $scope.action = 'Create';

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
                    var productId = response.data.id;
                    window.location.replace('#!/catalog/products/edit/' + productId);
                });
            };

        }
    ]
});