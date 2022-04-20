package com.dg3.forum.forum.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_pk;
	private String content_comment;
	private Long user_pk;
	private Long thread_pk;
}
