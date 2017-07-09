var app = angular.module('mainApp', []);

app.controller('UsersCtrl', function ($scope, $http) {
    $scope.users = [];

    $http.get('http://localhost:8080/getAllUsers')
        .then(function (response) {

            var data = response.data;
            $scope.users = data;
        });
 });

/*$http.get('https://api.github.com/users')
    .then(function (response) {

        var data = response.data;
        var status = response.status;
        var statusText = response.statusText;
        var headers = response.headers;
        var config = response.config;

        $scope.user = data;
        console.log(data);
    });*/
