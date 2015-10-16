
var app = angular.module('URLShortener', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

var host = location.hostname + (location.port ? ":" + location.port + "/" : "/");

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/newurl.html',
        controller: 'CreateCtrl'
    }).when('/stats', {
        templateUrl: 'views/create.html',
        controller: 'ListCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('CreateCtrl', function ($scope, $http, $location) {
 
	$scope.hostname = host;
	$scope.response = false;
    $scope.error = false;
    $scope.url = {}

    $scope.createUrl = function () {
        $http.post('/api/v1/todos', $scope.url).success(function (data) {
            $location.path('/');
            $scope.createdUrl = data.shortUrl;
            $scope.response = true;
            $scope.error = false;
        }).error(function (data, status) {
            console.log('Error ' + data)
            if(status == 501){
            	
            	$scope.textError = "This custom URL already exists. Please choose a new one...";
            	$scope.response = true;
                $scope.error = true;
                
            } if(status==502){
            	
            	$scope.textError = "The long URL is not correct!";
            	$scope.response = true;
            	$scope.error = true;
            	
            } if(status==503){
            	
            	$scope.textError = "The custom URL is not valid";
            	$scope.response = true;
            	$scope.error = true;
            }
        })
    }
});