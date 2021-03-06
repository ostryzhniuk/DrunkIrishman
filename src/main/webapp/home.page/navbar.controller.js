'use strict';

angular
.module('shopApp')
.controller('navbarCtrl', function ($http, $scope, $rootScope, $uibModal) {

    $scope.orderProp = 'name';

    $http.get('/currentUser').then(function(response) {
        $rootScope.user = response.data;
    });

    $rootScope.isAuthority = function (role) {
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

    $scope.logout = function () {
        $http.get('/logout');
        window.location.reload();
    };

    $http.get('/cartSize').then(function(response) {
        $rootScope.cartSize = response.data;
    });

    $http.get('/categories').then(function(response) {
        $scope.categories = response.data;
    });

    $scope.search = function () {
        if ($scope.searchParameter != undefined && $scope.searchParameter != "") {
            window.location.href = '#!/search/' + $scope.searchParameter;
        }
    };

    $scope.showCart = function () {
        $rootScope.modalInstance = $uibModal.open({
            component: 'cart',
            windowClass: 'app-modal-window',
            size: 'lg',
            backdrop: true
        });
    };

    /*$scope.showHistory = function (){
        alert("Sorry, but this service is temporarily unavailable.");
    };*/

});