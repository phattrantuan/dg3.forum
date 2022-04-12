package com.dg3.forum.forum.entity;

<<<<<<< HEAD
import lombok.Getter;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> phat
import lombok.Setter;

@Getter
@Setter
<<<<<<< HEAD
=======
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> phat
public class Image {
	private Long image_pk;
	private Long thread_pk;
	private String image_thread;
	private Long comment_pk;
	private String image_comment;
	private boolean enable_image ;
	
}
