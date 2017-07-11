var app = angular.module('mainApp', []);

app.controller('UsersCtrl', function ($scope, $http) {
    $scope.products = [];

    $http.get('/getProducts')
        .then(function (response) {

            var data = response.data;
            $scope.products = data;
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
