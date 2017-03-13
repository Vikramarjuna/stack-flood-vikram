/**
 * 
 */
angular.module('stack-flood.controllers', ['ngRoute','ngMaterial', 'stack-flood.services']).
 	controller('authController', function($scope, $routeParams, $location, postsservice) {
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
		if(uname && upassword){
			var data = {
					userName : uname,
					password : pwd
				};
				$.ajax({
					url : 'online/user/authenticate/',
					type : 'post',
					contentType : 'application/json',
					accept : 'application/json',
					data : JSON.stringify(data),
					success : function(response) {
						console.log('yes');
						$('.login-div').css('display','none');
						$('.login-page').css('display','none');
						populatePosts(uname);
					},
					error : function(error) {
						console.log('no');
						var jsonObj = JSON.parse(error.responseText);
						$('#uname').val('');
						$('#upassword').val('');
						$('#uname').css('border','1px solid red');
						$('#upassword').css('border','1px solid red');
						$('#loginErr').html(jsonObj.httpStatusMessage).addClass('error-msg');
						$('#loginErr').css('margin-bottom','10px');
						$('#loginErr').show();
					}
				});
		}
 	}
 	});