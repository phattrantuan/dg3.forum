package com.dg3.forum.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RepComment {
    private Long comment_rep_pk;
    private String content_comment_rep;
    private Long user_pk;
    private Long comment_pk;
    private boolean enable_rep_comment;
}
