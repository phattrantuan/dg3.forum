package com.dg3.forum.forum.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
	private Long image_pk;
	private Long thread_pk;
	private String image_thread;
	private Long comment_pk;
	private String image_comment;
	public Image(Long image_pk, Long thread_pk, String image_thread, Long comment_pk, String image_comment) {
		super();
		this.image_pk = image_pk;
		this.thread_pk = thread_pk;
		this.image_thread = image_thread;
		this.comment_pk = comment_pk;
		this.image_comment = image_comment;
	}
	public Image() {
		
	}
	
}
