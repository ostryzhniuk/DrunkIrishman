'use strict';

angular.module('catalogCategories', []);

angular.
module('catalogCategories').
component('catalogCategories', {
    templateUrl: '/catalog/categories/catalog-categories.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function CatalogCategoriesController($http, $scope, $rootScope) {

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

        }
    ]
});


function mouseOverCategory(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOutCategory(element){
    element.childNodes[1].style.visibility='hidden';
};
