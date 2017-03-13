/**
 * 
 */
angular.module('stack-flood.controllers', ['ngRoute','ngMaterial', 'stack-flood.services']).
 	controller('viewpostcontroller', function($scope,$rootScope, _post,$mdDialog, postsservice,userService) {
		$scope.post = _post;
		postsservice.addView($scope.post.postId);
		function saveReply(data){
			var data = {
					article : $scope.reply,
					createdBy : userService.username
				};
			postsservice.addReply(data,$scope.post.postId).then(function(added){
				$scope.post=added;
				//window.location="#/view/post/"+$scope.post.postId;
				
				alert = $mdDialog.alert({
			        title: 'Comment Saved',
			        textContent: 'Comment Saved successfully',
			        ok: 'Close'
			    });
			    $mdDialog
			    .show( alert )
			    .finally(function() {
			        alert = undefined;
			    });
			},function(error){
				console.log(error);
			});
		}
		$scope.addReply=function(e){
			if(!$scope.replyPost.$valid){
				$scope.showMsgs=true;
				return;
			}
			if(userService.isAuthenticated) {
				saveReply();
			}else{
				$rootScope.postLoginAction=saveReply;
				$mdDialog.show({
				      controller: 'authController',
				      templateUrl: 'app/views/signin.html',
				      parent: angular.element(document.body),
				      clickOutsideToClose:true,
				      scope: $scope,        // use parent scope in template
			          preserveScope: true,
				      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
				    })
				    .then(function(answer) {
				      console.log( 'You said the information was "' + answer + '".');
				    }, function() {
				      console.log( 'You cancelled the dialog.');
				    });
			}
			
		}
		$scope.cancel=function(e){
    		$location.path("/posts");
			$rootScope.$apply();
    	}
 	}).
	controller('listpostscontroller', function($scope, _posts) {
		console.log(_posts);
		$scope.posts=_posts;
 	}).
    controller('newPostcontroller', function($scope, $rootScope,$location, $mdDialog, postsservice, userService) {
    	$scope.showMsgs=false;
    	function createPost(data){
    		var data = {
					title : $scope.title,
					article : $scope.article,
					tags : $scope.tags.split(","),
					createdBy : userService.username
				};
    		postsservice.addPost(data).then(function(added){
    			$rootScope.layout.loading=false;
				alert = $mdDialog.alert({
			        title: 'Post Saved',
			        textContent: 'Post Saved successfully',
			        ok: 'Close'
			    });
			    $mdDialog
			    .show( alert )
			    .then(function(answer){
			    	$location.path("view/post/"+added.postId);
			    })
			    .finally(function() {
			        alert = undefined;
			    });
			},function(error){
				alert = $mdDialog.alert({
			        title: 'Error saving',
			        textContent: error,
			        ok: 'Close'
			    });
			    $mdDialog
			    .show( alert )
			    .finally(function() {
			        alert = undefined;
			    });
			});
    	}
    	$scope.cancel=function(e){
    		$location.path("/posts");
			$rootScope.$apply();
    	}
		$scope.addPost=function(e){
			if(!$scope.createPost.$valid){
				$scope.showMsgs=true;
				return;
			}
			$rootScope.layout.loading=true;
			if(userService.isAuthenticated) {
				createPost();
				
			}else{
				$rootScope.layout.loading=false;
				$rootScope.postLoginAction=createPost;
				$mdDialog.show({
				      controller: 'authController',
				      templateUrl: 'app/views/signin.html',
				      parent: angular.element(document.body),
				      clickOutsideToClose:true,
				      scope: $scope,        // use parent scope in template
			          preserveScope: true,
				      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
				    })
				    .then(function(answer) {
				      console.log( 'You said the information was "' + answer + '".');
				    }, function() {
				      console.log( 'You cancelled the dialog.');
				    });
			}
		}
	}).
	controller('authController', function($scope, $rootScope,$location, userService) {
		$scope.authenticate=function(e){
 		var uname = $scope.uname;
		var pwd = $scope.pwd;
		if(!uname || uname == ''){
			$('#unameError').html('Please enter user name.').addClass('error-msg');
			$('#unameError').css('margin-bottom','10px');
			$('#unameError').show();
		}
		if(!pwd || pwd == ''){
			$('#upwdErr').html('Please enter password.').addClass('error-msg');
			$('#upwdErr').css('margin-bottom','10px');
			$('#upwdErr').show();
		}
		if(uname && pwd){
			var data = {
					userName : uname,
					password : pwd
				};
			userService.authenticate(data).then(function(added){
				console.log(added);
				$rootScope.userid=uname;
				if($rootScope.postLoginAction!=null && (typeof $rootScope.postLoginAction === "function")){
					$rootScope.postLoginAction();
				}else{
					//$location.path("signin");
					$location.path("/posts");
					
					console.log('going to default page');
					//$rootScope.$apply();
				}
			},function(error){
				console.log(error);				
				$('#uname').val('');
				$('#upassword').val('');
				$('#uname').css('border','1px solid red');
				$('#upassword').css('border','1px solid red');
				$('#loginErr').html(error.statusMessage).addClass('error-msg');
				$('#loginErr').css('margin-bottom','10px');
				$('#loginErr').show();
			});				
		}
 	}
 	}).
 	controller('userController', function($scope, $rootScope,$mdDialog, $routeParams, $location, userService) {
 		
 		$scope.hide = function() {
		      $mdDialog.hide();
		    };
		    $scope.cancel = function() {
		      $mdDialog.cancel();
		    };
		    $scope.answer = function(answer) {
		      $mdDialog.hide(answer);
		    };
 		$scope.createUser=function(e){
		if($scope.pwd !== $scope.cnfpwd){
			$cope.pwdMatchError=true;
			$scope.addUser.$invalid=true;
		}
		if($scope.addUser.$valid){
			var data = {
				userName : $scope.name,
				fullName : $scope.fullName,
				password : $scope.pwd,
				email : $scope.email,
				phoneNumber : $scope.phonenumber
			};
			$rootScope.layout.loading=true;
			userService.addUser(data).then(function(added){
				$rootScope.layout.loading=false;
				alert = $mdDialog.alert({
			        title: 'User Registered',
			        textContent: 'Registration successfully',
			        ok: 'Close'
			    });
			    $mdDialog
			    .show( alert )
			    .then(function(answer) {
				    	$location.path("signin");
				    }, function() {
				    	$location.path("signin");
				    })
			    .finally(function() {
			        alert = undefined;
			    });
				/*
				console.log($scope.name);
				$mdDialog.show({
					parent: angular.element(document.body),
					  controller: 'userController',
				      templateUrl: 'app/views/RegistrationSuccess.html',
				      parent: angular.element(document.body),
				      targetEvent: e,
				      clickOutsideToClose:true,
				      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
				    })
				    .then(function(answer) {
				    	$location.path("signin");
				    }, function() {
				    	$location.path("signin");
				    });
				    */
			},function(error){
				$scope.showMsgs=true;
				console.log(error);
				console.log(error.responseText);
				if(error.status==409){
					$scope.userCreateErr="User already exists";
				}else if(error.responseText){
					$scope.userCreateErr=JSON.parse(error.responseText).httpStatusMessage;
				}else{
					$scope.userCreateErr="Unknown Error";
				}
			});
		}else{
			$scope.showMsgs=true;
		}
			
 		}
 	});