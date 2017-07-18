'use strict';

angular.module('shopApp', [
    'ngRoute',
    'ngStorage',
    'login',
    'signUp',
    'catalogProducts',
    'catalogCategories',
    'productDetail'
]);

angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/catalog', {
            template: '<catalog-categories></catalog-categories>'
        }).
        when('/catalog/:categoryName', {
            template: '<catalog-products></catalog-products>'
        }).
        when('/login', {
            template: '<login></login>'
        }).
        when('/signUp', {
            template: '<sign-up></sign-up>'
        }).
        when('/catalog/:categoryName/:productId', {
            template: '<product-detail></product-detail>'
        }).
        otherwise('/catalog');
    }
])