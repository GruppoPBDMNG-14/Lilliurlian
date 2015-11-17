
var app = angular.module('Lilliurlian', [
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
        templateUrl: 'views/stats.html',
        controller: 'StatsCtrl'
    }).when('/404', {
        templateUrl: 'views/404.html',
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('StatsCtrl', function ($scope, $http) {
	
	$scope.response = false;
    $scope.error = false;
    
	$scope.getStats = function () {
		$http.post('/api/v1/stats', $scope.url).success(function (data) {
			
			$scope.shortUrl = data.shortUrl;
			$scope.longUrl = data.longUrl;
			$scope.createdOn = data.createdOn;
			$scope.totalClicks = data.totalClicks;
			$scope.clicksPerDay = data.clicksPerDay;
			$scope.clicksPerMonth = data.clicksPerMonth;
			$scope.clicksPerYear = data.clicksPerYear;
			$scope.clicksPerCountry = data.clicksPerCountry;
			$scope.clicksPerBrowser = data.clicksPerBrowser;
			$scope.clicksPerOS = data.clicksPerOS;
			$scope.response = true;
			$scope.error = false;
		}).error(function (data, status){
			
			console.log('Error ' + data);
			
			if(status == 504){
				
				$scope.textError = "Short URL Not Found!";
				$scope.response = true;
				$scope.error = true;
			}
			
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
            console.log('Error ' + data);
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