package com.dg3.forum.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Like {
	private Long like_pk;
	private Long comment_pk;
	private Long user_pk;
	private Long thread_pk;

}
