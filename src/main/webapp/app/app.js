/**
 * 
 */
angular.module('stack-flood', ['ngRoute', 'stack-flood.controllers']).
	config(function($routeProvider) {
		$routeProvider.when('/register', {
			templateUrl : 'app/views/register.html',
		}).when('/signin', {
			templateUrl : 'app/views/signin.html',
		}).when('/newpost', {
			templateUrl : 'app/views/newpost.html',
		}).when('/view/post/:id', {
			templateUrl : 'app/views/viewpost.html',
			controller : 'viewpostcontroller'
		}).otherwise({
			 templateUrl : 'app/views/listposts.html',
			 controller : 'listpostscontroller'
		});
	});