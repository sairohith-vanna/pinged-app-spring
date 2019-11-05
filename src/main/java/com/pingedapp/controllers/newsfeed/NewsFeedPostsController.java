package com.pingedapp.controllers.newsfeed;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.appdto.NewPost;
import com.pingedapp.appmodels.Post;
import com.pingedapp.responses.PostDeleteResponse;
import com.pingedapp.services.NewsFeedManager;

@RestController
@RequestMapping("posts")
public class NewsFeedPostsController {
	
	@Autowired
	NewsFeedManager newsFeedManager;

	@PostMapping(value = "/create")
	public NewPost createPost(@RequestBody NewPost postCreateRequest) {
		try {
			Post post = newsFeedManager.createNewPost(postCreateRequest);
			NewPost newPostResponse = new NewPost();
			BeanUtils.copyProperties(newPostResponse, post);
			return newPostResponse;
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/getPosts")
	public List<Post> getAllPosts() {
		return newsFeedManager.getPosts();
	}
	
	@DeleteMapping(value = "/remove/{id}")
	public PostDeleteResponse deletePost(@PathVariable String id) {
		try {
		if(!newsFeedManager.deletePost(id)) {
			return PostDeleteResponse.POST_DELETED;
		}
		else {
			return PostDeleteResponse.POST_DOES_NOT_EXIST;
		}
		} catch(IllegalArgumentException ex) {
			return PostDeleteResponse.CANNOT_DELETE_POST;
		}
	}
	
	@PostMapping(value = "/edit/{id}")
	public Post updatePost(@PathVariable String id, @Valid @RequestBody String content) {
		return newsFeedManager.modifyPost(id, content);
	}
}
