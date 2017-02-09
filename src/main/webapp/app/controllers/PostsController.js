/**
 * 
 */
angular.module('stack-flood.controllers', ['ngRoute', 'stack-flood.services']).
 	controller('viewpostcontroller', function($scope, $routeParams, $location, postsservice) {
 		/*blogservice.fetch().then(function(){
 			$scope.blogs = blogservice.blogs();
 		});*/
		//console.log('Size: '+$scope.posts.length);
		$scope.post = postsservice.getPostById($routeParams.id);
		console.log($routeParams.id);
		/*$scope.getPost = function(postId) {
			$scope.post = postsservice.getPostById(postId);
			console.log('Done');
		};*/
 	}).
	controller('listpostscontroller', function($scope, $routeParams, $location, postsservice) {
 		/*blogservice.fetch().then(function(){
 			$scope.blogs = blogservice.blogs();
 		});*/
 		$scope.posts = [];
 		$scope.posts = postsservice.posts();
 	});