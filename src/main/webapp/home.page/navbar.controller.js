'use strict';

angular.
module('shopApp').
controller('navbarCtrl', function ($http, $scope, $rootScope, $uibModal) {

    $scope.orderProp = 'name';

    $http.get('/basketSize').then(function(response) {
        $rootScope.basketSize = response.data;
    });

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });

    $scope.search = function () {
        window.location.href = '#!/search/' + $scope.searchParameter;
    };

    $scope.showBasket = function () {
        $rootScope.modalInstance = $uibModal.open({
            component: 'basket',
            windowClass: 'app-modal-window',
            size: 'lg',
            backdrop: true,
            resolve: {

            }
        });
    };

});