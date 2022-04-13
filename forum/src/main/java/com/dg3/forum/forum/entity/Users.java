package com.dg3.forum.forum.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_pk;
    private String email;
    private String password;
    private String username;
    private String role;
    private String phone_number;
    private String address;
    private Date date_of_birth;
    private boolean ban_account;
    private String img_avatar;
    private String description;
    private Date created_date;
    private Date expire;
    private boolean enable_users;

}
