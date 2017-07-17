'use strict';

angular.
module('shopApp').
controller('navbarCtrl', function ($http, $scope) {

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });

    $scope.orderProp = 'name';

});