angular.module('catalog', [
    'ngRoute'
]);

angular.
module('catalog').
component('catalog', {
    templateUrl: '/catalog/catalog.template.html',
    controller: ['$http', '$scope',
        function LoginController($http, $scope) {

            var ctrl = this;

            $http.get('/products').then(function(response) {
                ctrl.products = response.data;
            });

        }
    ]
});