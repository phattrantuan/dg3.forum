package com.dg3.forum.forum.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "post_topic")
public class PostTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long post_topic_pk;
private String name_topic;
private boolean enable_post_topic;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "post_topic_pk")
Set<PostThread>postThreads;//data not duplicate
// a

}
