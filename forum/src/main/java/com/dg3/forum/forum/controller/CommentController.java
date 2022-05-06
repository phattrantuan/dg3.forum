package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.dto.CommentsDTO;
import com.dg3.forum.forum.entity.Comment;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.service.CommentService;
import com.dg3.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	/*
	* Create commment by user
	* Request is Long thread_pk and Object comment
	* Respone create comment in database
	* */
	@PostMapping("/create/posts/{thread_pk}")
	public ResponseEntity<Message> createComment(@PathVariable("thread_pk") Long thread_pk, @RequestBody Comment comment) {
		if (comment.getContent_comment() != "") {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			Users users = userService.findByEmail(userDetails.getUsername());

			comment.setUser_pk(users.getUser_pk());
			comment.setThread_pk(thread_pk);
			comment.setEnable_comment(true);

			return ResponseEntity.status(HttpStatus.OK).body(
					new Message("OK", "Create comment posts successfully", commentService.createCommentPosts(comment)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Message("Failed", "Content comment cannot be left blank", ""));
		}
	}

	/*
	* Update comment by user
	* Request is Long thread_pk, Long comment_pk and Object comment
	* Reponse update comment in database through thread_pk and commnet_pk
	 */
	@PutMapping("/update/posts/{thread_pk}/{comment_pk}")
	public ResponseEntity<Message> updateComment(@PathVariable("thread_pk") Long thread_pk, @PathVariable("comment_pk") Long comment_pk, @RequestBody Comment comment) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Users users = userService.findByEmail(userDetails.getUsername());

		Comment check_Comment = commentService.getByComment(comment_pk);

		if ((check_Comment.getUser_pk() == users.getUser_pk()) && (check_Comment.getThread_pk() == thread_pk)) {
			commentService.updateCommnet_Posts(comment_pk, comment.getContent_comment());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Message("OK", "Update comment posts successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("OK", "Can't edit comment posts", ""));
		}
	}

	/*
	* delete comment by user
	* Request is Long comment_pk
	* Reponse delete comment through comment_pk
	*/
	@DeleteMapping("/delete/posts/{comment_pk}")
	public ResponseEntity<Message> deleteComment(@PathVariable("comment_pk") Long comment_pk) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Users users = userService.findByEmail(userDetails.getUsername());

		Comment check_Comment = commentService.getByComment(comment_pk);

		if (check_Comment.getUser_pk() == users.getUser_pk()) {
			commentService.deleteComment(comment_pk);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new Message("OK", "Delete comment posts successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new Message("OK", "Can't delete comment posts", ""));
		}
	}
//
//    @GetMapping("/all/posts/{thread_pk}")
//    public ResponseEntity<Message> showComment_Posts(@PathVariable("thread_pk") Long thread_pk){
//        List<Comment> list = commentService.showAllComment_Posts(thread_pk);
//
//        return list.isEmpty() ?
//                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                        new Message("Failed", "Can't find list comment", "")
//                ) :
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new Message("OK", "List comment by posts", list)
//                );
//    }

	/*
	* Show Information list all commnet by post thread
	* Request is Long thread_pk
	* Reponse list comment by post thread
	*/
	@GetMapping("/all/posts/{thread_pk}")
	public ResponseEntity<Message> showAllComments_Posts(@PathVariable("thread_pk") Long thread_pk) {
		List<Comment> listComment = commentService.showAllComment_Posts(thread_pk);
		List<CommentsDTO> commentsDTOS = new ArrayList<>();

		for (Comment comment : listComment) {
			String getNameUser = userService.getUsersname(comment.getUser_pk());
			CommentsDTO commentDTO = new CommentsDTO();
			commentDTO.setComment_pk(comment.getComment_pk());
			commentDTO.setContent_comment(comment.getContent_comment());
			commentDTO.setEnable_comment(comment.isEnable_comment());
			commentDTO.setThread_pk(comment.getThread_pk());
			commentDTO.setUser_pk(comment.getUser_pk());
			commentDTO.setUsername(getNameUser);
			commentsDTOS.add(commentDTO);
		}

		return commentsDTOS.isEmpty()
				? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new Message("Failed", "Can't find list comment", ""))
				: ResponseEntity.status(HttpStatus.OK)
						.body(new Message("OK", "List comment by posts", commentsDTOS));
	}

}
