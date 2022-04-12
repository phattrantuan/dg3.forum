package com.dg3.forum.forum.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "post_topic")
public class PostTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long post_topic_pk;
private String name_topic;
public PostTopic(Long post_topic_pk, String name_topic) {
	super();
	this.post_topic_pk = post_topic_pk;
	this.name_topic = name_topic;
}
public PostTopic() {
	super();
}

}
