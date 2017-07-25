'use strict';

angular.module('basket', ['ui.bootstrap']);

angular.
module('basket').
component('basket', {
    templateUrl: '/basket/basket.template.html',
    controller: ['$http', '$scope', '$rootScope',
        function BasketController($http, $scope, $rootScope) {

            $http.get('/basket').then(function(response) {
                $scope.basket = response.data;
            });

            $http.get('/basketSize').then(function(response) {
                $scope.basketSize = response.data;
            });

            $scope.cancel = function () {
                $rootScope.modalInstance.close();
            };

            $scope.isEmpty = function isEmpty(){
                if ($scope.basketSize == 0) {
                    return true;
                } else {
                    return false;
                }
            };

        }
    ]
});


$(document).on('click', '.number-spinner button', function () {
    var btn = $(this),
        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
        newVal = 0;

    if (btn.attr('data-dir') == 'up') {
        newVal = parseInt(oldValue) + 1;
    } else {
        if (oldValue > 1) {
            newVal = parseInt(oldValue) - 1;
        } else {
            newVal = 1;
        }
    }
    btn.closest('.number-spinner').find('input').val(newVal);
});