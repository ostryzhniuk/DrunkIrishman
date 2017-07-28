'use strict';

angular.module('categoryEditor', [
    'ngRoute'
]);

angular.
module('categoryEditor').
component('categoryEditor', {
    templateUrl: '/catalog/categories/category.editor/category-editor.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function CategoryEditorController($http, $scope, $rootScope) {

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