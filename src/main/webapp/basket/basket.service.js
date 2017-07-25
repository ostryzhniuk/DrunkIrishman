'use strict';

angular.
module('basket')
.service('basketService',
    function($http) {

        this.addProduct = function (product) {

            $http({
                method: 'PUT',
                url: '/addToBasket',
                data: product
            }).then(function(response) {
                return response.data;
            });
        }

});