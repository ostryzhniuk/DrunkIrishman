'use strict';

angular.
module('shopApp').
controller('navbarCtrl', function ($http, $scope) {

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });
    $scope.orderProp = 'name';

    $scope.chooseCategory = function chooseCategory(category) {
        console.log(category);
    };

    /*$http({
     method: 'GET',
     url: '/authorize',
     data: {
     username: $scope.username,
     password: $scope.password
     }
     });*/

});