package com.dg3.forum.forum.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private Long comment_pk;
	private String content_comment;
	private Long user_pk;
	private Long thread_pk;
}
