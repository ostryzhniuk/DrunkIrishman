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
                    isCategoryPhoto = true;
                });
            };

            $scope.save = function () {
                $scope.successMessage = '';
                $scope.errorMessage = '';
                validatePhoto(getPhoto())
            };

            function validatePhoto(file) {
                if (file == undefined) {
                    if (photoBase64 == undefined || photoBase64 == '') {
                        $scope.errorMessage = 'Choose photo, please.';
                        return;
                    }
                    editCategory();
                    return;
                }
                encodeBase64(file);
            };

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
                var request = confirm('Are you sure?\nCategory "'
                    + category.name + '" will and not be displayed.');
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

var isCategoryPhoto = false;

function mouseOverEditCategory(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOutEditCategory(element){
    if (isCategoryPhoto) {
        element.childNodes[1].style.visibility='hidden';
    }
};

function readCategoryPhotoURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#photo').attr('src', e.target.result);
            isCategoryPhoto = true;
            document.getElementById('choose-photo-container').style.visibility='hidden';
        };

        reader.readAsDataURL(input.files[0]);
    }
};