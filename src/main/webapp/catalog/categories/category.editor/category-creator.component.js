'use strict';

angular.module('categoryCreator', []);

angular.
module('categoryCreator').
component('categoryCreator', {
    templateUrl: '/catalog/categories/category.editor/category-editor.template.html',
    controller: ['$http', '$scope',
        function CategoryCreatorController($http, $scope) {

            $scope.errorMessage = '';
            $scope.editor = false;
            $scope.action = 'Create';
            var photoBase64 = '';
            document.getElementById('choose-photo-container').style.visibility='visible';
            isCategoryPhoto = false;

            $scope.save = function () {
                $scope.errorMessage = '';
                validatePhoto(getPhoto());
            };

            function validatePhoto(file) {
                if (file == undefined) {
                    $scope.errorMessage = 'Choose photo, please.';
                    return;
                }
                encodeBase64(file);
            };

            function encodeBase64(file) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    photoBase64 = reader.result;
                    crateCategory();
                };
            };

            function getPhoto() {
                return document.getElementById('choose-photo-input').files[0];
            };

            function crateCategory() {
                $http({
                    method: 'POST',
                    url: '/category/create',
                    data: {
                        name: $scope.name,
                        photo: photoBase64
                    }
                }).then(function(response) {
                    var categoryName = $scope.name;
                    window.location.replace('#!/catalog/categories/edit/' + categoryName);
                },function errorCallback(response) {
                    if (response.status == 409) {
                        $scope.errorMessage = 'Such category already exists.';
                    } else {
                        $scope.errorMessage = 'Sorry, but system error occurred. Try again later, please.';
                    }
                });
            };

            document.getElementById('choose-photo-button').addEventListener("click", function() {
                document.getElementById('choose-photo-input').click();
            });

        }
    ]
});