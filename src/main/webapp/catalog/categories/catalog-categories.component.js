'use strict';

angular.module('catalogCategories', [
    'ngRoute'
]);

angular.
module('catalogCategories').
component('catalogCategories', {
    templateUrl: '/catalog/categories/catalog-categories.template.html',
    controller: ['$http', '$scope', '$routeParams',
        function CatalogCategoriesController($http, $scope, $routeParams) {

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

            $scope.orderProp = 'name';

        }
    ]
});