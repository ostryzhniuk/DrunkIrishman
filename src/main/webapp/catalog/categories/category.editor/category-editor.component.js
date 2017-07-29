'use strict';

angular.module('categoryEditor', [
    'ngRoute'
]);

angular.
module('categoryEditor').
component('categoryEditor', {
    templateUrl: '/catalog/categories/category.editor/category-editor.template.html',
    controller: ['$http', '$scope', '$routeParams',
        function CategoryEditorController($http, $scope, $routeParams) {

            $scope.errorMessage = '';
            $scope.successMessage = '';

            $http.get('/category/' + $routeParams.categoryName).then(function(response) {
                $scope.category = response.data;
                $scope.name = $scope.category.name;
            });

            $scope.save = function () {
                $scope.errorMessage = '';
                $scope.successMessage = '';
                $http({
                    method: 'PUT',
                    url: '/category/update',
                    data: {
                        id: $scope.category.id,
                        name: $scope.name
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        $scope.successMessage = 'Category saved successfully';
                    }
                },function errorCallback(response) {
                    if (response.status == 409) {
                        $scope.errorMessage = 'Such category already exists';
                        console.log('response.status: ' + response.status);
                    } else {
                        $scope.errorMessage = 'Sorry, but system error occurred. Try again later, please.';
                    }
                });
            };

        }
    ]
});