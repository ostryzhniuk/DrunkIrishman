'use strict';

angular.module('categoryCreator', []);

angular.
module('categoryCreator').
component('categoryCreator', {
    templateUrl: '/catalog/categories/category.editor/category-editor.template.html',
    controller: ['$http', '$scope',
        function CategoryCreatorController($http, $scope) {

            var photoBase64 = '';

            $scope.save = function () {
                getBase64(getPhoto());
            };

            function crateCategory() {
                console.log(photoBase64);
                $http({
                    method: 'POST',
                    url: '/category/create',
                    data: {
                        name: $scope.name,
                        photo: photoBase64
                    }
                }).then(function(response) {

                });
            }

            function getBase64(file) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    photoBase64 = reader.result;
                    crateCategory();
                };
                /*reader.onerror = function (error) {
                    console.log('Error: ', error);
                };*/
            };

            function getPhoto() {
                return document.getElementById('choosePhoto').files[0];
            };

        }
    ]
});