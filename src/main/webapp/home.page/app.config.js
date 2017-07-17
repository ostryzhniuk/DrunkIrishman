'use strict';

angular.module('shopApp', [
    'ngRoute',
    'login',
    'signUp',
    'catalogProducts'
]);

angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        /*when('/', {
            template: '<catalog></catalog>'
        }).
        when('/catalog', {
            template: '<catalog></catalog>'
        }).*/
        when('/catalog/:categoryName', {
            template: '<catalog-products></catalog-products>'
        }).
        when('/login', {
            template: '<login></login>'
        }).
        when('/signUp', {
            template: '<sign-up></sign-up>'
        });
        // otherwise('/login');
    }
])