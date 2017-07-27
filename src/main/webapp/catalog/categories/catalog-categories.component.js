'use strict';

angular.module('catalogCategories', [
    'ngRoute'
]);

angular.
module('catalogCategories').
component('catalogCategories', {
    templateUrl: '/catalog/categories/catalog-categories.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function CatalogCategoriesController($http, $scope, $rootScope) {

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

            $scope.orderProp = 'name';

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

            $scope.createCategory = function() {

            }

        }
    ]
});