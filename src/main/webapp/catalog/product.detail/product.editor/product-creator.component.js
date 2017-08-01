'use strict';

angular.module('productCreator', []);

angular.
module('productCreator').
component('productCreator', {
    templateUrl: '/catalog/product.detail/product.editor/product-editor.template.html',
    controller: ['$http', '$scope',
        function ProductCreatorController($http, $scope) {

            $scope.errorMessage = '';
            $scope.editor = false;
            $scope.action = 'Create';
            var photoBase64 = '';
            document.getElementById('choose-photo-container').style.visibility='visible';
            isProductPhoto = false;

            $http.get('/categories').then(function(response) {
                $scope.categories = response.data;
            });

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
                    crateProduct();
                };
            };

            function getPhoto() {
                return document.getElementById('choose-photo-input').files[0];
            };

            function crateProduct() {
                $http({
                    method: 'POST',
                    url: '/product/create',
                    data: {
                        name: $scope.name,
                        price: $scope.price,
                        capacity: $scope.capacity,
                        category: $scope.category,
                        description: $scope.description,
                        photo: photoBase64
                    }
                }).then(function(response) {
                    var productId = response.data.id;
                    window.location.replace('#!/catalog/products/edit/' + productId);
                },function errorCallback(response) {
                    $scope.errorMessage = 'Sorry, but an error occurred. Try again again, please.';
                });
            };

            document.getElementById('choose-photo-button').addEventListener("click", function() {
                document.getElementById('choose-photo-input').click();
            });

        }
    ]
});