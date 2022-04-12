package com.dg3.forum.forum.entity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Image {
	private Long image_pk;
	private Long thread_pk;
	private String image_thread;
	private Long comment_pk;
	private String image_comment;
	private boolean enable_image ;
	
}
