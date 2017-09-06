var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/linkedin',{
            templateUrl: 'views/linkedin.html',
            controller: 'linkedinController'
        })
        .when('/curriculumvitae',{
            templateUrl: 'views/curriculum.html',
            controller: 'curriculumController'
        })
        .when('/contactus',{
            templateUrl: 'views/contactus.html',
            controller: 'contactusController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
