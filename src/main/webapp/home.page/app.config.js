angular.
module('shopApp').
config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/login', {
            template: '<login></login>'
        });
        /*when('/phones/:phoneId', {
            template: '<phone-detail></phone-detail>'
        }).
        otherwise('/login');*/
    }
]);