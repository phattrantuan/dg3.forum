package com.dg3.forum.forum.entity;

import lombok.Data;

@Data
public class Comment {
	private Long comment_pk;
	private String content_comment;
	private Long user_pk;
	private Long thread_pk;
	public Comment(Long comment_pk, String content_comment, Long user_pk, Long thread_pk) {
		super();
		this.comment_pk = comment_pk;
		this.content_comment = content_comment;
		this.user_pk = user_pk;
		this.thread_pk = thread_pk;
	}
	public Comment() {
		
	}
	
}
