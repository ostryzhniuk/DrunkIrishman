'use strict';

angular.module('shopApp', [
    'ngRoute',
    'login',
    'signUp',
    'catalogProducts',
    'catalogCategories',
    'productDetail',
    'ui.bootstrap',
    'basket',
    'searchProduct'
]);

angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider', '$qProvider',
    function config($locationProvider, $routeProvider, $qProvider) {
        $locationProvider.hashPrefix('!');
        $qProvider.errorOnUnhandledRejections(false);

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
        when('/search/:searchParameter', {
            template: '<search-product></search-product>'
        }).
        otherwise('/catalog');
    }
]);
