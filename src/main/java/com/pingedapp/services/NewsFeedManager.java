package com.pingedapp.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingedapp.appdto.NewComment;
import com.pingedapp.appdto.NewPost;
import com.pingedapp.appmodels.Comment;
import com.pingedapp.appmodels.Post;
import com.pingedapp.repositories.CommentRepository;
import com.pingedapp.repositories.PostRepository;

@Service
public class NewsFeedManager {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;

	public Post createNewPost(NewPost newPost) throws IllegalAccessException, InvocationTargetException {
		Post post = new Post();
		BeanUtils.copyProperties(post, newPost);
		return postRepository.save(post);
	}

	public boolean deletePost(String id) {
		if (postRepository.existsById(id)) {
			try {
				Iterable<Comment> postComments = postRepository.findById(id).get().getComments();
				commentRepository.deleteAll(postComments);
				postRepository.deleteById(id);
				commentRepository.deleteAll(postComments);
			} catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException();
			}
		} else {
			return false;
		}
		return postRepository.existsById(id);
	}

	public List<Post> getPosts() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}

	public Post modifyPost(String id, String newPostContent) {
		if (postRepository.existsById(id)) {
			Post post = postRepository.findById(id).orElse(null);
			post.setPostContent(newPostContent);
			postRepository.save(post);
			return post;
		} else {
			return null;
		}

	}

	public Post addCommentToPost(String postId, NewComment comment)
			throws IllegalAccessException, InvocationTargetException {
		Post post = postRepository.findById(postId).orElse(null);
		if (post != null) {
			Comment newComment = new Comment();
			BeanUtils.copyProperties(newComment, comment);
			if (post.getComments() == null) {
				List<Comment> initialComment = Arrays.asList(commentRepository.save(newComment));
				post.setComments(initialComment);
			} else {
				post.getComments().add(commentRepository.save(newComment));
			}
			return postRepository.save(post);
		}
		return null;
	}

	public Post deleteCommentForPost(String postId, String commentId) {
		commentRepository.deleteById(commentId);
		if (!commentRepository.existsById(commentId)) {
			Post postForDeletedComment = postRepository.findById(postId).orElse(null);
			postForDeletedComment.getComments().removeIf((comment) -> {
				return comment.getId().equals(commentId);
			});

			return postRepository.save(postForDeletedComment);
		}
		return null;
	}

	public Post editCommentForPost(String postId, String commentId, String comment) {
		if (postRepository.existsById(postId)) {
			Post post = postRepository.findById(postId).get();
			//Comment savedComment = commentRepository.findById(commentId).get();
			List<Comment> comments = post.getComments();
			Comment matchedComment = comments.stream().filter(cmtx -> {
				return cmtx.getId().equals(commentId);
			}).findFirst().get();
			int commentIdx = comments.indexOf(matchedComment);
			comments.get(commentIdx).setComment(comment);
			post.setComments(comments);
			post = postRepository.save(post);
			commentRepository.save(comments.get(commentIdx));
			return post;
		}
		return null;
	}
}
