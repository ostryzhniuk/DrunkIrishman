angular.module('signUp', [
    'ngRoute'
]);

angular.
module('signUp').
component('signUp', {
    templateUrl: '/sign.up/sign-up.template.html',
    controller: ['$http', '$scope',
        function SignUpController($http, $scope) {

            $scope.submit = function(){
                $http({
                    method: 'POST',
                    url: '/signUp',
                    data: {
                        email: $scope.email,
                        password: $scope.password,
                        name: $scope.firstName,
                        surname: $scope.lastName,
                        date: $scope.birthDate,
                        address: $scope.address,
                        phone: $scope.phone}
                });
            }

        }
    ]
});



function checkPass() {

    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');

    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if(pass1.value == pass2.value){

        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match"
    }else{

        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
}

function validatePhone(phone) {
    var maintainplus = '';
    var numval = phone.value
    if (numval.charAt(0)=='+') {
        var maintainplus = '';
    }
    curphonevar = numval.replace(/[\\A-Za-z!"£$%^&\,*+_={};:'@#~,.Š\/<>?|`¬\]\[]/g,'');
    phone.value = maintainplus + curphonevar;
    maintainplus = '';
    phone.focus;
}

function validateText(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z-'\n\r.]+/g, '');
}

function validateEmail(email) {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if(regMail.test(email) == false)
    {
        document.getElementById("status").innerHTML    = "<span class='warning'>Email address is not valid yet.</span>";
    }
    else
    {
        document.getElementById("status").innerHTML	= "<span class='valid'>Thanks, you have entered a valid Email address!</span>";
    }
}

function validateAddress(address) {
    address.value = address.value.replace(/[^A-Za-z0-9'\.\-\s\,]/, '');
}

/*function validateAddress(address) {
    var regAdd = /^\\d+ [a-zA-Z ]+, \\d+ [a-zA-Z ]+, [a-zA-Z ]+$/;

    if(regAdd.test(address) == false)
    {
        document.getElementById("statusAdd").innerHTML	= "<span class='warning'>Address is not valid yet.</span>";
    }
    else
    {
        document.getElementById("statusAdd").innerHTML	= "<span class='valid'>Thanks, Address looks valid!</span>";
    }
}*/
