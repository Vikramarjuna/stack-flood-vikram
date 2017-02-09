/**
 * 
 */
angular.module('stack-flood.services', []).
	service('postsservice', function($http) {
		var posts = [{
			'postId' : '1',
			'viewCount' : '0',
			'replyCount' : '0',
			'title' : 'Title Prasanna Title Prasanna Title Prasanna Title Prasanna Title Prasanna',
			'tags' : ['tags', 'tags', 'tags'],
			'createdBY' : 'Prasanna Ramakrishna',
			'createdDate' : 'Thu Jan 19 2017 19:12:21'
			},
			{
			'postId' : '2',
			'viewCount' : '0',
			'replyCount' : '0',
			'title' : 'Title Shruthi Title Shruthi Title Shruthi Title Shruthi Title Shruthi',
			'tags' : ['tags', 'tags', 'tags'],
			'createdBY' : 'Shruthi Prasanna',
			'createdDate' : 'Thu Jan 19 2017 19:12:21'
			}];
		//var posts = [];
	    this.fetch = function(){
	    	var promise = $http.get('online/posts/').success(function(data){
	    		if(typeof(response) !== "undefined" && response !== null){
					if(response.length > 0){
						posts = response;
					}else{
						posts = [];
					}
	    		}else{
	    			posts = [];
	    		}	
			}).error(function(e){
				posts = [];
			});
			return promise;
	    };
	    
	    this.posts = function(){
	    	return posts;
	    };
	    
	    this.getPostById = function(postId){
	    	var postData = $.grep(posts, function(post, index){
		    	if(post.postId == postId){
		          	return post;
		        }
		    });
		  
			return postData[0];
	    }
	});