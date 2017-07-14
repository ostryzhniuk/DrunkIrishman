angular.
module('login').
factory('Login', ['$resource',
    function($resource) {
        return $resource('/angular.test/app/resources/phones/:phoneId.json', {}, {
            query: {
                method: 'GET',
                params: {phoneId: 'phones'},
                isArray: true
            }
        });
    }
]);
