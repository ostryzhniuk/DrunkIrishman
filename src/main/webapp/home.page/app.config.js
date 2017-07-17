'use strict';

angular.module('shopApp', [
    'ngRoute',
    'login',
    'signUp',
    'catalog'
]);

angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/', {
            template: '<catalog></catalog>'
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