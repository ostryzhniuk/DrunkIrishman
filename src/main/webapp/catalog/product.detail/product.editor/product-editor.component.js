'use strict';

angular.module('productEditor', [
    'ngRoute'
]);

angular.
module('productEditor').
component('productEditor', {
    templateUrl: '/catalog/product.detail/product.editor/product-editor.template.html',
    controller: ['$http', '$scope', '$routeParams',
        function ProductEditorController($http, $scope, $routeParams) {

            $scope.errorMessage = '';
            $scope.successMessage = '';
            $scope.editor = true;
            $scope.action = 'Edit';
            var photoBase64 = '';

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

            $http.get('/product/' + $routeParams.productId).then(function(response) {
                $scope.product = response.data;
                $scope.name = $scope.product.name;
                $scope.category = $scope.product.category;
                $scope.price = $scope.product.price;
                $scope.capacity = $scope.product.capacity;
                $scope.description = $scope.product.description;
                $scope.status = $scope.product.status;
                loadPhoto($scope.product.id);
            });

            $http.get('/statusList').then(function(response) {
                $scope.statusList = response.data;
            });

            function loadPhoto(productId) {
                $http.get('/product/image/' + productId).then(function(response) {
                    $scope.photo = 'data:image/jpeg;base64,' + response.data;
                    photoBase64 = $scope.photo;
                    isProductPhoto = true;
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
                    editProduct();
                    return;
                }
                encodeBase64(file);
            };

            function encodeBase64(file) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    photoBase64 = reader.result;
                    editProduct();
                };
            };

            function getPhoto() {
                return document.getElementById('choose-photo-input').files[0];
            };

            function editProduct () {
                $scope.status = document.getElementById('status').value;
                $http({
                    method: 'PUT',
                    url: '/product/update',
                    data: {
                        id: $scope.product.id,
                        name: $scope.name,
                        price: $scope.price,
                        capacity: $scope.capacity,
                        category: $scope.category,
                        description: $scope.description,
                        photo: photoBase64,
                        status: $scope.status
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        $scope.successMessage = 'Product saved successfully';
                    }
                },function errorCallback(response) {
                    $scope.errorMessage = 'Sorry, but an error occurred. Try again again, please.';
                });
            };

            $scope.delete = function (product) {
                var request = confirm('Are you sure?\nProduct "'
                    + product.name + '" will and not be displayed.');
                if (request == true) {
                    $http({
                        method: 'PUT',
                        url: '/product/deactivate',
                        data: product
                    }).then(function(response) {
                        if (response.status == 200) {
                            var categoryName = product.category.name;
                            window.location.replace('#!/catalog/' + categoryName);
                        }
                    });
                };
            };

            document.getElementById('choose-photo-button').addEventListener("click", function() {
                document.getElementById('choose-photo-input').click();
            });

        }
    ]
});


var isProductPhoto = false;

function mouseOverEditProduct(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOutEditProduct(element){
    if (isProductPhoto) {
        element.childNodes[1].style.visibility='hidden';
    }
};

function readProductPhotoURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#photo').attr('src', e.target.result);
            isProductPhoto = true;
            document.getElementById('choose-photo-container').style.visibility='hidden';
        };

        reader.readAsDataURL(input.files[0]);
    }
};