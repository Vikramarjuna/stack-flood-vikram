/**
 * 
 */
var app=angular.module('stack-flood', ['ngRoute', 'stack-flood.controllers'])
	app.config(function($routeProvider) {
		$routeProvider.when('/register', {
			templateUrl : 'app/views/register.html',
			controller : 'userController'
		}).when('/signin', {
			templateUrl : 'app/views/signin.html',
				controller : 'authController'
		}).when('/newpost', {
			templateUrl : 'app/views/newpost.html',
			controller : 'newPostcontroller'
		}).when('/view/post/:id', {
			templateUrl : 'app/views/viewpost.html',
			resolve:{
                _post: function($route,postsservice){
               	 	return postsservice.getPostById($route.current.params.id);
                }
			 },
			controller : 'viewpostcontroller'
		}).when('/posts', {
			templateUrl : 'app/views/listposts.html',
			 resolve:{
                    _posts: function(postsservice){
                   	 return postsservice.fetch();
                    }
				 },
		     controller : 'listpostscontroller' 
		}).otherwise({
			 templateUrl : 'app/views/listposts.html',
			 resolve:{
                     _posts: function(postsservice){
                    	 return postsservice.fetch();
                     }
				 },
		     controller : 'listpostscontroller' 
		});
	});

/*CONFIG*/
app.run(function ($rootScope, $location,$route, $timeout) {
    $rootScope.config = {};
    $rootScope.config.app_url = $location.url();
    $rootScope.config.app_path = $location.path();
    $rootScope.layout = {};
    $rootScope.layout.loading = false;

    $rootScope.$on('$routeChangeStart', function () {
        $timeout(function(){
          $rootScope.layout.loading = true;          
        });
    });
    $rootScope.$on('$routeChangeSuccess', function () {
        $timeout(function(){
          $rootScope.layout.loading = false;
        }, 200);
    });
    $rootScope.$on('$routeChangeError', function () {
        $rootScope.layout.loading = false;
    });
});

