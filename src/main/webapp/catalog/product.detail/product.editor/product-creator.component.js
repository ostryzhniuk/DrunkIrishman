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

            }

        }
    ]
});