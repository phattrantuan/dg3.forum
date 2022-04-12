package com.dg3.forum.forum.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {
	private Long like_pk;
	private Long comment_pk;
	private Long user_pk;
	private Long thread_pk;
	public Like(Long like_pk, Long comment_pk, Long user_pk, Long thread_pk) {
		super();
		this.like_pk = like_pk;
		this.comment_pk = comment_pk;
		this.user_pk = user_pk;
		this.thread_pk = thread_pk;
	}
	public Like() {
		
	}
	
}
