package com.dg3.forum.forum.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.sym.Name;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post_thread")

public class PostThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thread_pk;
    private String title_thread;
    private Date time_post_thread;
    private String content_of_thread;
    private Long user_pk;
    private boolean enable_post_thread;
    
    @OneToOne (cascade = CascadeType.ALL)//update tables there are related links 
    @JoinColumn(name = "post_topic_pk")
    PostTopic postTopic;
    
    
//		private String post_toppic;
//		private String post_toppic_pk;

//		@OneToOne(cascade = CascadeType.ALL)//update tables involve
//		@JoinColumn(name = "user_pk")
//		Users users;

}