'use strict';

angular.module('categoryCreator', []);

angular.
module('categoryCreator').
component('categoryCreator', {
    templateUrl: '/catalog/categories/category.editor/category-editor.template.html',
    controller: ['$http', '$scope',
        function CategoryCreatorController($http, $scope) {

            $scope.save = function () {
                $http({
                    method: 'POST',
                    url: '/category/crate',
                    data: {
                        name: $scope.name
                    }
                }).then(function(response) {

                });
            }

        }
    ]
});