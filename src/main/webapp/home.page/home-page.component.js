angular.module('shopApp', [
    'ngRoute'
]);

angular.
module('shopApp').
component('shopApp', {
    templateUrl: '/home.page/index.html',
    controller: ['$http', '$scope',
        function LoginController($http, $scope) {

            var ctrl = this;

            $http.get('/getCategories').then(function(response) {
                ctrl.categories = response.data;
            });

        }
    ]
});
