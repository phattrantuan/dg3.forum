package com.dg3.forum.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	private Long comment_pk;
	private String content_comment;
	private Long user_pk;
	private Long thread_pk;
	private boolean enable_comment ;
	
}
