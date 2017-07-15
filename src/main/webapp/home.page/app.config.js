angular.module('shopApp', [
    'ngRoute',
    'login',
    'signUp'
]);

angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/login', {
            template: '<login></login>'
        }).
        when('/signUp', {
            template: '<sign-up></sign-up>'
        });
        // otherwise('/login');
    }
]);