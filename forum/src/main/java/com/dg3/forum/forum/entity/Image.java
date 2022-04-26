package com.dg3.forum.forum.entity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long image_pk;
	private Long thread_pk;
	private String image_thread;
	private Long comment_pk;
	private String image_comment;
	private boolean enable_image;
	
}
