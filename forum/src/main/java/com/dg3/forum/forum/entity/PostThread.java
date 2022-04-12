package com.dg3.forum.forum.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post_thread")
public class PostThread {
<<<<<<< HEAD

	@Getter
	@Setter
	@NoArgsConstructor
	@Entity
	@Table(name = "post_thread")
	public class PostTopic {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long thread_pk;
		private String title_thread;
		private Date time_post_thread;
		private String content_of_thread;
		private String post_topic_pk;
		private Long user_pk;
=======
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long thread_pk;
	private String title_thread;
	private Date time_post_thread;
	private String content_of_thread;
	private String post_topic_pk;
	private Long user_pk;
	private boolean enable_post_thread ;
>>>>>>> phat
//		private String post_toppic;
//		private String post_toppic_pk;

//		@OneToOne(cascade = CascadeType.ALL)//update tables involve
//		@JoinColumn(name = "user_pk")
//		Users users;

}