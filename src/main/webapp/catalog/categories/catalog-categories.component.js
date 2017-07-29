'use strict';

angular.module('catalogCategories', [
    'ngRoute'
]);

angular.
module('catalogCategories').
component('catalogCategories', {
    templateUrl: '/catalog/categories/catalog-categories.template.html',
    controller: ['$http', '$scope', '$routeParams', '$rootScope',
        function CatalogCategoriesController($http, $scope, $rootScope) {

            $http.get('/categories').then(function(response) {
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
                var request = confirm('Are you sure?\nThis action CANNOT be undone! This will ' +
                    'permanently delete the ' + category.name + ' category and all products of this category.');
                if (request == true) {
                    $http.delete('/category/delete/' + category.id).then(function(response) {
                        window.location.reload();
                    });
                };
            };

        }
    ]
});


function mouseOver(element){
    element.childNodes[1].style.visibility='visible';
};

function mouseOut(element){
    element.childNodes[1].style.visibility='hidden';
};
