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
            $scope.editor = true;
            $scope.action = 'Edit';
            var photoBase64 = '';

            $http.get('/category/' + $routeParams.categoryName).then(function(response) {
                $scope.category = response.data;
                $scope.name = $scope.category.name;
                loadPhoto($scope.category.id);
            });

            function loadPhoto(categoryId) {
                $http.get('/category/image/' + categoryId).then(function(response) {
                    $scope.photo = 'data:image/jpeg;base64,' + response.data;
                    photoBase64 = $scope.photo;
                });
            };

            $scope.save = function () {
                $scope.errorMessage = '';
                validatePhoto(getPhoto())
            };

            function validatePhoto(file) {
                if (file == undefined) {
                    if (photoBase64 == undefined || photoBase64 == '') {
                        $scope.errorMessage = 'choose photo';
                        return;
                    }
                    editCategory();
                    return;
                }
                encodeBase64(file);
            }

            function encodeBase64(file) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    photoBase64 = reader.result;
                    editCategory();
                };
            };

            function getPhoto() {
                return document.getElementById('choose-photo-input').files[0];
            };

            function editCategory () {
                $scope.errorMessage = '';
                $scope.successMessage = '';
                $http({
                    method: 'PUT',
                    url: '/category/update',
                    data: {
                        id: $scope.category.id,
                        name: $scope.name,
                        photo: photoBase64
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        $scope.successMessage = 'Category saved successfully';
                    }
                },function errorCallback(response) {
                    if (response.status == 409) {
                        $scope.errorMessage = 'Such category already exists';
                    } else {
                        $scope.errorMessage = 'Sorry, but system error occurred. Try again later, please.';
                    }
                });
            };

            $scope.delete = function (category) {
                var request = confirm('Are you sure?\nThis action CANNOT be undone! This will ' +
                    'permanently delete the ' + category.name + ' category and all products of this category.');
                if (request == true) {
                    $http({
                        method: 'PUT',
                        url: '/category/deactivate',
                        data: category
                    }).then(function(response) {
                        if (response.status == 200) {
                            window.location.replace('#!/catalog/');
                        }
                    });
                }
            };

            document.getElementById('choose-photo-button').addEventListener("click", function() {
                document.getElementById('choose-photo-input').click();
            });

        }
    ]
});

function mouseOverEditCategory(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOutEditCategory(element){
    element.childNodes[1].style.visibility='hidden';
};

function readCategoryPhotoURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#photo').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
};