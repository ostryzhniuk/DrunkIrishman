angular.
module('login').
component('login', {
    templateUrl: '/login/login.template.html',
    controller: ['$http',
        function LoginController($http) {



        }
    ]
});


function showPassword() {

    var key_attr = $('#key').attr('type');

    if(key_attr != 'text') {
        $('.checkbox').addClass('show');
        $('#key').attr('type', 'text');
    } else {
        $('.checkbox').removeClass('show');
        $('#key').attr('type', 'password');
    }
}