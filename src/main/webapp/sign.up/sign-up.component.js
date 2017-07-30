'use strict';

angular.module('signUp', [
    'ngRoute'
]);

angular.
module('signUp').
component('signUp', {
    templateUrl: '/sign.up/sign-up.template.html',
    controller: ['$http', '$scope',
        function SignUpController($http, $scope) {

            $scope.errorMessage = '';

            $scope.minBirthDate = getMinBirthDate();

            function getMinBirthDate() {
                var date = new Date();
                date.setFullYear(date.getFullYear() - 21);
                return date.toISOString().substring(0, 10);
            };

            $scope.submit = function(){
                $scope.errorMessage = '';
                var dateStr = $scope.birthDate.toISOString().substring(0, 10);

                if (!validateEmail($scope.email) || !checkPass()) {
                    return;
                }

                $http({
                    method: 'POST',
                    url: '/signUp',
                    data: {
                        email: $scope.email,
                        password: $scope.password,
                        name: $scope.firstName,
                        surname: $scope.lastName,
                        birthDate: dateStr,
                        address: $scope.address,
                        phone: $scope.phone}
                }).then(function(response) {
                    $http({
                        method: 'POST',
                        url: '/authorize',
                        data: {
                            email: $scope.email,
                            password: $scope.password
                        }
                    }).then(function() {
                        window.location.reload();
                        window.location.replace('#!/');
                    });
                },function errorCallback(response) {
                    if (response.status == 409) {
                        $scope.errorMessage = 'Email address already exists.';
                    } else {
                        $scope.errorMessage = 'Sorry, but system error occurred. Try again later, please.';
                    }
                });
            };

        }
    ]
});

function checkPass() {

    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');

    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if(pass1.value == pass2.value) {
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "";
        return true;
    } else {
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!";
        return false;
    }
}

function validatePhone(phone) {
    phone.value = phone.value.replace(/[^0-9]/, '');
}

function validateName(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z-'\n\s\r.]+/g, '');
    if (txt.value.length == 1) {
        txt.value = txt.value.charAt(0).toUpperCase();
    }
}

function validateEmail(email) {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if(regMail.test(email) == false) {
        document.getElementById("status").innerHTML = "<span class='warning'>Email address is not valid yet.</span>";
        return false;
    } else {
        document.getElementById("status").innerHTML	= "";
        return true;
    }
}

function validateAddress(address) {
    address.value = address.value.replace(/[^A-Za-z0-9'\.\-\s\,()\\]/, '');
}
