/**
 * 
 */
angular.module('stack-flood.services', []).
	service('postsservice', function($http,$q) {
		var posts = undefined;
		this.fetch = function(forceGet){
	    	if (!posts || forceGet) {
	            // create deferred object using $q
	            var deferred = $q.defer();
	            // get posts form backend
	            $http.get('online/posts/')
	              .then(function(result) {
	                // save fetched posts to the local variable
	                posts = result.data;
	                console.log(posts)
	                // resolve the deferred
	                deferred.resolve(posts);
	              }, function(error) {
	                posts = error;
	                deferred.reject(error);
	              });
	            // set the posts object to be a promise until result comeback
	            posts = deferred.promise;
	          }
	          return $q.when(posts);
	    };
	    var post=undefined;
	    this.getPostById = function(postId){
	    	if(posts){
	    	var postData = $.grep(posts, function(posti, index){
		    	if(posti.postId == postId){
		          	return posti;
		        }
		    });
	    	post=postData[0];
	    	}else{
	    		var deferred=$q.defer();
	    		$http.get('online/post/'+postId)
	              .then(function(result) {
	            	post = result.data;
	                deferred.resolve(post);
	              }, function(error) {
	                posts = error;
	                deferred.reject(error);
	              });
	    		post=deferred.promise;
	    	}
	    	return $q.when(post);
	    }
	    this.addPost=function(post){
	    	var deferred = $q.defer();
	    	var added;
	    	var req = {
	    		 method: 'POST',
	    		 url: 'online/post/',
	    		 headers: {
	    		   'Content-Type': 'application/json'
	    		 },
	    		 data:JSON.stringify(post)
	    	}
	    	var that=this;
	    	$http(req).then(function(response) {
	    	  added=response.data;
	    	  that.fetch().then(function(posts){
	    		  posts.push(added);
	    	  });
	    	  
	    	  deferred.resolve(response.data);
			},function(error) {
			  added = error;
              deferred.reject(error);
			});
	    	added = deferred.promise;
	    	return $q.when(added);
	    }
	    this.addReply=function(post,postid){
	    	var deferred = $q.defer();
	    	var added;
	    	var req = {
	    		 method: 'PUT',
	    		 url: 'online/post/'+postid+'/replies',
	    		 headers: {
	    		   'Content-Type': 'application/json'
	    		 },
	    		 data:JSON.stringify(post)
	    	}
	    	$http(req).then(function(response) {
	    	 added=response.data;
	    	 if(posts){
	 	    	var postData = $.grep(posts, function(posti, index){
	 		    	if(posti.postId == postid){
	 		          	return posti;
	 		        }
	 		    });
	 	    	post=postData[0];
	 	    	post.replies.push(added);
	 	    	post.replyCount++;
	    	 }
	    	 deferred.resolve(response.data);
			},function(error) {
			 added = error;
             deferred.reject(error);
		    });
	        added = deferred.promise;
	        return $q.when(added);
	    }
	    this.addView=function(postid){
	    	var req = {
	    		 method: 'PUT',
	    		 url: 'online/post/'+postid+'/views',
	    		 headers: {
	    		   'Content-Type': 'application/json'
	    		 }
	    	}
	    	$http(req).then(function(response){
	    		if(posts){
		 	    	var postData = $.grep(posts, function(posti, index){
		 		    	if(posti.postId == postid){
		 		          	return posti;
		 		        }
		 		    });
		 	    	post=postData[0];
		 	    	post.viewCount++;
		    	 }
	    	});
	    }
	}).
	service('userService', function($http,$q) {
		this.isAuthenticated=false;
		this.username='';
		this.addUser=function(data){
			var deferred = $q.defer();
	    	var added;
	    	var req = {
	    		 method: 'POST',
	    		 url : 'online/user/',
	    		 headers: {
	    		   'Content-Type': 'application/json'
	    		 },
	    		 data:JSON.stringify(data)
	    	}
	    	$http(req).then(function(response) {
	    	 added=response.data;
	    	 deferred.resolve(response.data);
			},function(error) {
			 added = error;
             deferred.reject(error);
		    });
	        added = deferred.promise;
	        return $q.when(added);
		}
		this.authenticate=function(data){
			var deferred = $q.defer();
			var added;
	    	var req = {
	    		 method: 'POST',
	    		 url : 'online/user/authenticate/',
	    		 headers: {
	    		   'Content-Type': 'application/json'
	    		 },
	    		 data:JSON.stringify(data)
	    	}
	    	var that=this;
	    	$http(req).then(function(response) {
		    	 added=response.data;
		    	 if(added.statusCode==200){
		    		 that.isAuthenticated=true;
		    		 that.username=data.userName;
		    	 }		    	
		    	 deferred.resolve(response.data);
				},function(error) {
					console.log(error);
				 added = error;
	             deferred.reject(error);
			    });
		        added = deferred.promise;
		        return $q.when(added);			
		}
	});