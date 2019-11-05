package com.pingedapp.controllers.newsfeed;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.appdto.NewComment;
import com.pingedapp.appmodels.Post;
import com.pingedapp.services.NewsFeedManager;

@RestController
@RequestMapping("/comments")
public class NewsFeedCommentsController {
	
	@Autowired
	private NewsFeedManager newsFeedManager;
	
	@PostMapping("/push/{id}")
	public Post addComment(@RequestBody NewComment comment, @PathVariable String id) {
		try {
			return newsFeedManager.addCommentToPost(id, comment);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{postId}/{commentId}")
	public Post deleteComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
		return newsFeedManager.deleteCommentForPost(postId, commentId);
	}
	
	@PostMapping("/edit/{postId}/{commentId}")
	public Post editComment(@RequestBody String comment, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
		return newsFeedManager.editCommentForPost(postId, commentId, comment);
	}
}
