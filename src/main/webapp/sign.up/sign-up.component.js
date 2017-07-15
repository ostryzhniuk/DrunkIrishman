angular.module('signUp', [
    'ngRoute'
]);

angular.
module('signUp').
component('signUp', {
    templateUrl: '/sign.up/sign-up.template.html',
    controller: ['$http', '$scope',
        function LoginController($http, $scope) {

            /*$scope.submit = function(){
                $http({
                    method: 'POST',
                    url: '/postEmail',
                    data: {email: $scope.email,
                        password: $scope.password}
                });
            }*/

        }
    ]
});