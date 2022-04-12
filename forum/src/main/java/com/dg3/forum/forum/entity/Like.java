package com.dg3.forum.forum.entity;

<<<<<<< HEAD

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> phat
public class Like {
	private Long like_pk;
	private Long comment_pk;
	private Long user_pk;
	private Long thread_pk;

	
}
