package com.dg3.forum.forum.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepComment {
	private Long comment_rep_pk;
	private String content_comment_rep;
	private Long user_pk;
	private Long comment_pk;
	public RepComment(Long comment_rep_pk, String content_comment_rep, Long user_pk, Long comment_pk) {
		super();
		this.comment_rep_pk = comment_rep_pk;
		this.content_comment_rep = content_comment_rep;
		this.user_pk = user_pk;
		this.comment_pk = comment_pk;
	}
	public RepComment() {
		
	}
}
