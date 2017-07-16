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
                    data: {username: $scope.username,
                            password: $scope.password}
                });
            }

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