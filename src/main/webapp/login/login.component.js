'use strict';

angular.module('login', [
    'ngRoute'
]);

angular.
module('login').
component('login', {
    templateUrl: '/login/login.template.html',
    controller: ['$http', '$scope',
        function LoginController($http, $scope) {

            $scope.submit = function(){
                $http({
                    method: 'POST',
                    url: '/authorize',
                    data: {
                        email: $scope.email,
                        password: $scope.password
                    }
                }).then(function(response) {
                    if (response.status == 200) {
                        window.location.reload();
                        window.location.replace('#!/');
                    }
                    console.log(response.status);
                },function errorCallback(response) {
                    console.log(response.status);
                });
            };

        }
    ]
});


function showPassword() {

    var key_attr = $('#password').attr('type');

    if(key_attr != 'text') {
        $('.checkbox').addClass('show');
        $('#password').attr('type', 'text');
    } else {
        $('.checkbox').removeClass('show');
        $('#password').attr('type', 'password');
    }
}