'use strict';

angular.module('catalogCategories', []);

angular.
module('catalogCategories').
component('catalogCategories', {
    templateUrl: '/catalog/categories/catalog-categories.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function CatalogCategoriesController($http, $scope, $rootScope) {

            var csvBase64 = '';

            $http.get('/categories?loadImage=true').then(function(response) {
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

            $scope.delete = function (category) {
                var request = confirm('Are you sure?\nCategory "'
                    + category.name + '" will and not be displayed.');
                if (request == true) {
                    $http({
                        method: 'PUT',
                        url: '/category/deactivate',
                        data: {
                            id: category.id,
                            name: category.name
                        }
                    }).then(function(response) {
                        if (response.status == 200) {
                            window.location.reload();
                        }
                    });
                };
            };

            document.getElementById('open-csv-button').addEventListener("click", function() {
                document.getElementById('open-csv-input').click();
            });

            csvProductReader.onload = function () {
                csvBase64 = csvProductReader.result;
                createProducts();
            };

            function createProducts() {
                $http({
                    method: 'POST',
                    url: '/product/create/byCsv',
                    data: {
                        base64SourceData: csvBase64
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        alert('Data has been successfully saved!');
                    }
                },function errorCallback(response) {
                    alert('Sorry, but an error occurred.\n' +
                        'Maybe You data is wrong. Try again, please.');
                });
            };

        }
    ]
});

var csvProductReader = new FileReader();

function mouseOverCategory(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOutCategory(element){
    element.childNodes[1].style.visibility='hidden';
};

function readCSVinCategories(input) {
    if (input.files && input.files[0]) {
        csvProductReader.readAsDataURL(input.files[0]);
    }
};
