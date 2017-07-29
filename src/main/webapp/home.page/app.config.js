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
    'searchProduct',
    'categoryEditor',
    'categoryCreator',
    'productCreator',
    'productEditor'
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
        when('/catalog/categories/edit/:categoryName', {
            template: '<category-editor></category-editor>'
        }).
        when('/catalog/categories/create/', {
            template: '<category-creator></category-creator>'
        }).
        when('/catalog/products/create/', {
            template: '<product-creator></product-creator>'
        }).
        when('/catalog/products/edit/:productId', {
            template: '<product-editor></product-editor>'
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
